package com.debugers.alltv.controller;

import com.debugers.alltv.model.LiveRoom;
import com.debugers.alltv.result.Result;
import com.debugers.alltv.service.EGameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "企鹅电竞")
@RestController
@RequestMapping("api/egame")
public class EGameController {
    private final EGameService eGameService;

    public EGameController(EGameService eGameService) {
        this.eGameService = eGameService;
    }

    @ApiOperation("获取直播真实地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomId", value = "房间号", required = true, dataType = "String"),
    })

    @GetMapping("real_url/{roomId}")
    public Result<Map<String, String>> getRealUrl(@PathVariable(value = "roomId", required = true) String roomId){
        Map<String, String> result = new HashMap<>();
        result.put("realUrl", eGameService.getRealUrl(roomId));
        return Result.success(result);
    }
    @GetMapping("top_rooms")
    public Result<List<LiveRoom>> getTopRooms(Integer pageNum, Integer pageSize){
        return Result.success(eGameService.getTopRooms(pageNum,pageSize));
    }
    @GetMapping("checkLiveStatus")
    public Result<Boolean> checkLiveStatus(String roomId){
        String url = eGameService.getRealUrl(roomId);
        return Result.success(url.contains("http"));
    }
}
