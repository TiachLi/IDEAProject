package com.service;

import com.domain.*;

import java.util.List;

public interface HouseService {

   public List<House> findAllHouse();

    int findTotalCounts();

    HousePageBean pageQueryWithCondition(int currentPage, int pageSize, House house);

    ResultInfo addHouse(House house);

    public  void  deleteHouse(int houseId);

    public House findOneById(int id);

    public ResultInfo updateHouse(House house);
}
