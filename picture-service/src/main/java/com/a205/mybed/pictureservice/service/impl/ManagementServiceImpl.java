package com.a205.mybed.pictureservice.service.impl;

import com.a205.mybed.pictureservice.dao.*;
import com.a205.mybed.pictureservice.exception.ResourceNotFoundException;
import com.a205.mybed.pictureservice.pojo.*;
import com.a205.mybed.pictureservice.service.ManagementService;
import com.a205.mybed.pictureservice.util.FileUtil;
import com.a205.mybed.pictureservice.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagementServiceImpl implements ManagementService {
    Logger logger = LoggerFactory.getLogger(ManagementService.class);
    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private UserAlbumMapper userAlbumMapper;
    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    private AlbumPictureMapper albumPictureMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Value("${picture-url-prefix}")
    private String urlPrefix;
    @Autowired
    private FileUtil fileUtil;


    @Override
    @Transactional
    public Album createAlbum(int userID, String newAlbumName) {
        // 创建相册
        Album album = new Album();
        album.setName(newAlbumName);
        album.setCreateTime(new Date());
        albumMapper.insertSelective(album);
        // 插入关系表
        UserAlbum userAlbum = new UserAlbum();
        userAlbum.setAlbumId(album.getId());
        userAlbum.setUserId(userID);
        userAlbumMapper.insert(userAlbum);
        logger.info("用户(" + userID + ") ->新建相册 " + album.getName());
        return album;
    }

    /**
     * 点赞某图片，赞数加1
     *
     * @param picID 图片ID
     * @return 受影响的行数
     */
    @Override
    public int likePic(int picID) {
        // 赞数自增
        return pictureMapper.likeIncrUpdateByPrimaryKey(picID);
    }

    /**
     * 根据用户ID获取相册
     *
     * @param uid
     * @return
     */
    @Override
    public List<Album> getAlbumsByUserID(int uid) {
        return albumMapper.selectByUserID(uid);
    }

    /**
     * 根据用户ID获取他的所有图片
     * (临时在代码里做一下空集判断)
     *
     * @param uid
     * @return
     * @throws URISyntaxException
     */
    @Override
    public List<PictureDTO> getPicByUserID(int uid) throws URISyntaxException {
        List<PictureDTO> result = new ArrayList<>();
        // 获取对应相册id
        UserAlbumExample ua = new UserAlbumExample();
        ua.or().andUserIdEqualTo(uid);
        List<UserAlbum> uaList = userAlbumMapper.selectByExample(ua);
        if (uaList.size() == 0) return result;
        List<Integer> aidList = uaList.stream().map(UserAlbum::getAlbumId).collect(Collectors.toList());
        // 根据相册id查图片id
        AlbumPictureExample ap = new AlbumPictureExample();
        ap.or().andAlbumIdIn(aidList);
        List<Integer> pidList = albumPictureMapper.selectByExample(ap).stream().map(AlbumPicture::getPictureId).collect(Collectors.toList());
        if (pidList.size() == 0) return result;
        // 查出图片
        List<Picture> pics = pictureMapper.selectByPidList(pidList);
        // 装填数据
        for (Picture i : pics) {
            result.add(buildPictureDTO(i));
        }
        return result;
    }

    /**
     * 根据相册Id获取所有图片
     *
     * @param aid 相册id
     * @return
     */
    @Override
    public List<PictureDTO> getPicByAlbumID(int aid) throws URISyntaxException {
        List<Picture> pics = pictureMapper.selectByAlbumID(aid);
        List<PictureDTO> res = new ArrayList<>(pics.size());
        for (Picture i : pics) {
            res.add(buildPictureDTO(i));
        }
        return res;
    }

    /**
     * 获取某图片详情
     *
     * @param pid 图片ID
     * @return
     */
    @Override
    public PictureDTO getPicByPicID(int pid) throws URISyntaxException, ResourceNotFoundException {
        Picture p = pictureMapper.selectByPrimaryKey(pid);
        if (p == null)
            throw new ResourceNotFoundException();
        return buildPictureDTO(p);
    }

    /**
     * 在相册中删除某图片
     * @param pid 图片id
     * @param aid 相册id
     * @param uid 用户id
     * @return
     */
    @Override
    public boolean deletePicInAlbum(int pid, int aid, int uid) {
        int affected = albumPictureMapper.deleteByPrimaryKey(aid, pid);
        return affected > 0;
    }

    /**
     * 由Picture构建PictureDTO
     *
     * @param p
     * @return
     * @throws URISyntaxException
     */
    private PictureDTO buildPictureDTO(Picture p) throws URISyntaxException {
        PictureDTO dto = new PictureDTO(p);
        dto.setUrl(fileUtil.buildPicUrl(p));
        return dto;
    }


}
