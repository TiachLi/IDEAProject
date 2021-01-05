package com.service;

import com.domain.Admin;
import com.domain.ResultInfo;

public interface AdminService {
     ResultInfo register(Admin admin);
     ResultInfo login(Admin admin);
}
