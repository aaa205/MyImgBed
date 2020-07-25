package com.a205.mybed.pictureservice.service.impl;

import com.a205.mybed.pictureservice.dao.AlbumPictureMapper;
import com.a205.mybed.pictureservice.dao.PictureMapper;
import com.a205.mybed.pictureservice.pojo.*;
import com.a205.mybed.pictureservice.service.TransferService;
import com.a205.mybed.pictureservice.util.FileUtil;
import com.a205.mybed.pictureservice.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import util.MD5Util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {
    Logger logger = LoggerFactory.getLogger(TransferService.class);

    @Value("${storage-path}")
    private String storagePath;
    @Value("${picture-url-prefix}")
    private String urlPrefix;
    @Autowired
    private FileUtil fileUtil;
    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    private AlbumPictureMapper albumPictureMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Value("${cache-expire-second}")
    private int cacheExpireSecond;// 缓存过期时间
    @Value("${cache-prefix.picture}")
    private String cachePicKeyPrefix;// picture在redis中key的前缀

    /**
     * 上传图片
     *
     * @param file    待上传的图片
     * @param albumID 要上传到的相册
     * @return
     */
    @Transactional
    @Override
    public PictureDTO upload(MultipartFile file, int albumID) throws Exception {
        logger.info("上传图片" + file.getOriginalFilename());
        // 判断后缀名是否在可以保存的类型内
        String names[] = file.getOriginalFilename().split("\\.");
        String fileSuffix = names[names.length - 1];
        if (!fileUtil.isAllowed(fileSuffix))
            throw new Exception("文件格式错误");
        // 计算MD5
        String md5 = MD5Util.generateMD5(file.getInputStream());
        logger.info("文件MD5为:" + md5);
        Picture picture = getPicByMD5(md5); // 数据库里找有没有已保存，有的话直接取出
        if (picture == null) {
            logger.info("文件不存在，开始保存");
            picture = savePic(file, fileSuffix, albumID, md5);// 如果找不到，则保存
        }
        // 装填数据
        AlbumPicture ap = new AlbumPicture();
        ap.setAlbumId(albumID);
        ap.setPictureId(picture.getId());
        // 检查数据库中是否已存在关系
        AlbumPictureExample example = new AlbumPictureExample();
        example.or().andAlbumIdEqualTo(ap.getAlbumId()).andPictureIdEqualTo(ap.getPictureId());
        if (albumPictureMapper.selectByExample(example) == null) {
            // 保存相册-图片关系
            albumPictureMapper.insert(ap);
            logger.info("保存关系到数据库");
        }
        // 填充返回结果
        PictureDTO data = new PictureDTO(picture);
        data.setUrl(fileUtil.buildPicUrl(picture));
        // 保存到缓存中
        redisUtil.setex(cachePicKeyPrefix+data.getId(),cacheExpireSecond,data);
        return data;
    }


    /**
     * 将文件保存到硬盘和数据库中
     *
     * @param file
     * @param albumID
     * @param md5
     * @return
     * @throws IOException
     */
    private Picture savePic(MultipartFile file, String type, int albumID, String md5) throws IOException {
        // 确定图片保存相对路径
        String parent = fileUtil.getPicRelativePath();
        File destParent = new File(storagePath + parent);
        if (!destParent.exists())
            destParent.mkdirs();
        // 保存信息到数据库
        Picture picture = new Picture();
        picture.setMd5(md5);
        picture.setName(md5);// 用md5做文件名
        picture.setType(type);
        picture.setParentLocation(parent);
        BigDecimal decimal = new BigDecimal(fileUtil.sizeInMB(file.getSize()));
        picture.setSize(decimal);//图片大小 MB为单位
        logger.info("文件大小：" + picture.getSize() + "MB");
        picture.setLike(0);
        // 处理时间
        Calendar calendar = Calendar.getInstance();
        picture.setUploadTime(calendar.getTime());
        calendar.add(Calendar.YEAR, 1);
        picture.setExpireTime(calendar.getTime());
        pictureMapper.insert(picture);

        // 保存图片
        Path dest = Paths.get(storagePath, parent, md5 + "." + type);
        file.transferTo(dest);
        logger.info("保存图片至相册" + albumID + "--文件路径：" + dest);
        return picture;
    }

    /**
     * 根据MD5搜索数据库是否已保存该图片
     *
     * @param md5 要搜索图片的MD5
     * @return 找到的Picture，如果存在，否则返回null
     */
    public Picture getPicByMD5(String md5) {
        PictureExample example = new PictureExample();
        example.createCriteria().andMd5EqualTo(md5);
        List<Picture> list = pictureMapper.selectByExample(example);
        return list.size() > 0 ? list.get(0) : null;
    }
}
