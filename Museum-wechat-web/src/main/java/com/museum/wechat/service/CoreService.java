package com.museum.wechat.service;

import com.museum.pojo.ExhibitsInfo;
import com.museum.service.ItemInfoService;
import com.museum.wechat.pojo.News;
import com.museum.wechat.pojo.NewsMessage;
import com.museum.wechat.pojo.TextMessage;
import com.museum.wechat.utils.MessageUtil;
import com.museum.wechat.utils.WeixinUtil;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Component
public class CoreService {
    @Autowired
    private ItemInfoService itemInfoService;

    @Autowired
    private  WeixinUtil weixinUtil;
    private static Logger log =  Logger.getLogger(CoreService.class);

    private static String emoji(int codePoint) {
        return String.valueOf(Character.toChars(codePoint));
    }

    /**
     * 处理微信发来的请求
     *
     * @param request
     * @return
     */

    public  String processRequest(HttpServletRequest request, HttpServletResponse response) {

        // 默认返回的文本消息内容
        String respMessage = null;
        try {
            String respContent = null;
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            String fromUserName = requestMap.get("FromUserName");
            String toUserName = requestMap.get("ToUserName");
            String msgType = requestMap.get("MsgType");

            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);


            NewsMessage newsMessage=new NewsMessage();
            newsMessage.setToUserName(fromUserName);
            newsMessage.setFromUserName(toUserName);
            newsMessage.setCreateTime(new Date().getTime());
            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
            newsMessage.setFuncFlag(0);
            //文字信息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                respContent = "请点击菜单进行操作";
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            } // 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "请点击菜单进行操作";
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
            } // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "请点击菜单进行操作";
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }
            // 音频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "请点击菜单进行操作";
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 订阅
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                   String eventKey =requestMap.get("EventKey");
                   if(eventKey.startsWith("qrscene_")){
                       weixinUtil.processWechatMag(fromUserName, weixinUtil.getToken());
                       String[] s = eventKey.split("_");
                       News News =new News();
                       List<News> list=new ArrayList<>();
                       ExhibitsInfo exhibitsInfoResult= itemInfoService.getExhibitsInfoById(Integer.valueOf(s[1]));
                       if(exhibitsInfoResult==null){
                           News.setTitle("抱歉,该展品已经被删除!!!!");
                           News.setPicUrl(null);
                           News.setUrl(null);
                           News.setDescription("抱歉,该展品已经被删除!!!!");
                           list.add(News);
                           newsMessage.setArticleCount(1);
                           newsMessage.setArticles(list);
                           respMessage= MessageUtil.newsMessageToXml(newsMessage);
                           return respMessage;
                       }
                       News.setTitle(exhibitsInfoResult.getName());
                       News.setPicUrl("http://ptljizme7.bkt.clouddn.com/"+exhibitsInfoResult.getImgName());
                       News.setUrl("http://t777cs.natappfree.cc/detail-"+s[1]);
                       News.setDescription(exhibitsInfoResult.getInfo());
                       list.add(News);
                       newsMessage.setArticleCount(1);
                       newsMessage.setArticles(list);
                       respMessage= MessageUtil.newsMessageToXml(newsMessage);
                       return respMessage;
                   }
                    String mag = weixinUtil.processWechatMag(fromUserName, weixinUtil.getToken());
                    respContent = emoji(0x1F334) +"欢迎您:"+mag+","+"谢谢您关注";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                //取消订阅
                }else if(eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)){
                    weixinUtil.noProcessWechatMag(fromUserName);
                }
                // 自定义菜单点击事件
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    String eventKey = requestMap.get("EventKey");
                    if (eventKey.equals("11")) {
                    } else if (eventKey.equals("12")) {
                    }
                }else if (eventType.equals("scancode_push")){

                    String eventKey = requestMap.get("EventKey");
                    if(eventKey.equals("rselfmenu_0_1")){

                    }
                }else if (eventType.equals("SCAN")){
                    String eventKey =requestMap.get("EventKey");
                    News News =new News();
                    List<News> list=new ArrayList<>();
                    ExhibitsInfo exhibitsInfoResult= itemInfoService.getExhibitsInfoById(Integer.valueOf(eventKey));
                    if(exhibitsInfoResult==null){
                        News.setTitle("抱歉,该展品已经被删除!!!!");
                        News.setPicUrl(null);
                        News.setUrl(null);
                        News.setDescription("抱歉,该展品已经被删除!!!!");
                        list.add(News);
                        newsMessage.setArticleCount(1);
                        newsMessage.setArticles(list);
                        respMessage= MessageUtil.newsMessageToXml(newsMessage);
                        return respMessage;
                    }
                    News.setTitle(exhibitsInfoResult.getName());
                    News.setPicUrl("http://ptljizme7.bkt.clouddn.com/"+exhibitsInfoResult.getImgName());
                    News.setUrl("http://t777cs.natappfree.cc/detail-"+eventKey);
                    News.setDescription(exhibitsInfoResult.getInfo());
                    list.add(News);
                    newsMessage.setArticleCount(1);
                    newsMessage.setArticles(list);
                    respMessage= MessageUtil.newsMessageToXml(newsMessage);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }
}