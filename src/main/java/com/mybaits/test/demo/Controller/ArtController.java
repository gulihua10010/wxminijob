package com.mybaits.test.demo.Controller;

import com.mybaits.test.demo.Common.JSONResult;
import com.mybaits.test.demo.bean.Art;
import com.mybaits.test.demo.service.ArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.util.JAXBSource;
import java.util.List;

@RestController
public class ArtController {


    @Autowired
    ArtService artService;
    @PostMapping("/api/getarts")
    public JSONResult getarts(Integer  sid){
        List<Art> arts=artService.getArtBysid(sid);
        return  JSONResult.ok(arts);

    }
    @PostMapping("/api/getartsbyid")
    public JSONResult getartsbyid(Integer  id){
        Art arts=artService.getArtByid(id);
        return  JSONResult.ok(arts);

    }
}
