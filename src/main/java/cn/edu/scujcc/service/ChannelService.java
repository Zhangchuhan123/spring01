package cn.edu.scujcc.service;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.scujcc.api.ChannelController;
import cn.edu.scujcc.dao.ChannelRepository;
import cn.edu.scujcc.model.Channel;

/**
package cn.edu.scujcc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import cn.edu.scujcc.model.Channel;

/**
 * 
 */

@Service
public class ChannelService {
	@Autowired
	private ChannelRepository repo;
	public static final Logger logger =LoggerFactory.getLogger(ChannelService.class);
	
	
	public List<Channel>getAllChannel(){
	return repo.findAll();
}

	/**
	 * 
	 * @param channelId 
	 * @return
	 */
     public Channel getChannel(String channelId) {
    	Optional<Channel> result = repo.findById(channelId);
    	if(result.isPresent()) {
    		return result.get();
    		}else {
    			return null;
    		}
     }
     /**
      * 
      * @param id
      * return
      */
     public boolean deleteChannel(String channelId) {
    	 boolean result = true;
    	 repo.deleteById(channelId);
    	 return result;
     }
     public Channel createChannel(Channel c) {
     	 return repo.save(c);
     }
     /**
      * 更新指定后的频道信息
      * @param c
      * @return
      */
     public Channel updateChannel(Channel c) {
    	 Channel saved = getChannel(c.getId());
    	 if(saved !=null) {
    		 if(c.getTitle() !=null) {
    			 saved.setTitle(c.getTitle());
    		 }
    		 if(c.getQuality() !=null) {
        		 saved.setQuality(c.getQuality());
    		 }
    		 if(c.getUrl() !=null) {
    			 saved.setUrl(c.getUrl());
    	 }
    		 if(c.getComments() !=null) {
    			 saved.getComments().addAll(c.getComments());
    	 }else {
    		 saved.setComments(c.getComments());
    	 }
    		 logger.debug(saved.toString());
    	 return repo.save(saved);
    	 
    	 }
		return saved;
     }
     /**
      * 
      *  @param title
      * @return quality
      * @return
      */
     public List<Channel>findChannelByTitle(String title){
    	 return repo.findByTitle(title);
     }
     public List<Channel>findChannelByQuality(String quality){
    	 return repo.findByQuality(quality);
    
     
     }
}


     

     



