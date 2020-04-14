package com.ttn.core.models;

import com.adobe.cq.sightly.WCMUsePojo;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.poifs.property.Child;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import sun.rmi.transport.ObjectTable;

import java.util.*;

public class ImportantLinkWCM extends WCMUsePojo {
    private List<HashMap<String,Object>> impLink = new ArrayList<HashMap<String,Object>>();

    @Override
    public void activate() throws Exception {
        Resource currentResource = getResource();
        Resource linksData = currentResource.getChild("linksData");
        ResourceResolver resourceResolver = getResourceResolver();
        for (Resource child : linksData.getChildren()) {
            ValueMap childMap = child.getValueMap();
            HashMap<String, Object> map = new HashMap<>(childMap);
            String pagelink = (String) childMap.get("pagelink");
            Resource res = resourceResolver.getResource(pagelink);
            if(res!=null){
                map.put("pagelink",pagelink+".html");
            }

            impLink.add(map);
        }
    }
    public List<HashMap<String,Object>> getImpLink() {
        return impLink;
    }
}
