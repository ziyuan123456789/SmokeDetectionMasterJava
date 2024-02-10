package com.example.chapter6_wzy.Service.Port;

import com.example.chapter6_wzy.Bean.Port.Union.ChannelInfoWithPort;

import java.util.List;

/**
 * @author wsh
 */
public interface ChannelService {
    List<ChannelInfoWithPort> getAllChannelInfo();
}
