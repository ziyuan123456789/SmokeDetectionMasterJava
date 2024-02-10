package com.example.chapter6_wzy.Service.Port.Impl;

import com.example.chapter6_wzy.Bean.Port.Union.ChannelInfoWithPort;
import com.example.chapter6_wzy.Mapper.Port.ChannelMapper;
import com.example.chapter6_wzy.Service.Port.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChannelServiceImpl implements ChannelService {
    @Autowired
    ChannelMapper channelMapper;
    @Override
    public List<ChannelInfoWithPort> getAllChannelInfo() {
        return channelMapper.getAllChannelInfo();
    }
}
