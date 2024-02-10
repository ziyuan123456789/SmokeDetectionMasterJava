package com.example.chapter6_wzy.Service.Port;

import com.example.chapter6_wzy.Bean.Port.Anchorage_info;

import java.util.List;

/**
 * @author wsh
 */
public interface AnchorageService {
    List<Anchorage_info> getAnchorageList();
}
