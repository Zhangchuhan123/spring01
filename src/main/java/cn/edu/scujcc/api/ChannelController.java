package cn.edu.scujcc.api;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.scujcc.model.Channel;
import cn.edu.scujcc.service.ChannelService;
/**
 * 
 * @param id
 * @return
 */
@RestController   
@RequestMapping("/channel")
public class ChannelController {
	public static final Logger logger =LoggerFactory.getLogger(ChannelController.class);
	@Autowired
	private ChannelService service;
	/**
	 * 
	 * @return
	 */
	@GetMapping
	public List<Channel>getALLChannels() {
		logger.info("正在读取所有频道信息...");
		List<Channel>results=service.getAllChannel();
		
		return results;
		
	}
	/**
	 * 
	 * @param idָ
	 * @return id
	 */
	
	@GetMapping("/{id}")
	public Channel getChannel(@PathVariable String id) {
		Channel c=service.getChannel(id);
		if(c!=null) {
			return c;
		}else {
			logger.error("找不到指定的频道。");
			return null;
		
		}
	}
	/**
	 * 
	 * @param id 
	 * @return 
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String>deleteChannel(@PathVariable String id){
		boolean result = this.service.deleteChannel(id);
		if(result) {
		return ResponseEntity.ok().body("删除成功");
		}else {
			return ResponseEntity.ok().body("删除失败");
		}
	}
	/**
	 * 
	 * @param 
	 * @return 
	 */
	@PostMapping
	public Channel createChannel(@RequestBody Channel c) {
		return this.service.createChannel(c);
	}
	@PutMapping
	public Channel updateChannel(@RequestBody Channel c) {
		return this.service.updateChannel(c);
	}
	@GetMapping("/t/{title}")
	public List<Channel> findChannelT(@PathVariable String title) {
		return this.service.findChannelByTitle(title);
	}
	@GetMapping("/q/{quality}")
	public List<Channel> findChannelQ(@PathVariable String quality) {
		return this.service.findChannelByQuality(quality);
		
	}
}
	
	
	
	