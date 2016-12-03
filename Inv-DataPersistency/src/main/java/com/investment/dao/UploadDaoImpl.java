package com.investment.dao;

import org.springframework.stereotype.Repository;

import com.investment.dao.root.RootDaoImpl;
import com.investment.entity.Upload;

@Repository
public class UploadDaoImpl extends RootDaoImpl<Upload> implements UploadDao{

}


