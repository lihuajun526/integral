package com.operational.platform.spider.component.analyzer.json.qq;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.operational.platform.spider.bean.ParseResult;
import com.operational.platform.spider.component.JsonAnalyzer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 2017/7/23.
 */
public class QQUserAnalyzer extends JsonAnalyzer {

    private static final Logger LOGGER = LoggerFactory.getLogger(QQUserAnalyzer.class);

    @Override
    public List<ParseResult> parse(String jsonResponse) {

        List<ParseResult> list = new ArrayList<>();
        JSONObject result = JSONObject.parseObject(jsonResponse);
        if (result.getInteger("retcode").intValue() != 0)
            return list;
        JSONArray datas = result.getJSONObject("result").getJSONObject("buddy").getJSONArray("info_list");
        for (int i = 0; datas != null && i < datas.size(); i++) {
            ParseResult parseResult = new ParseResult();
            JSONObject data = datas.getJSONObject(i);
            parseResult.setLink(data.getString("uin"));
            try {
                parseResult.setTitle(URLEncoder.encode(data.getString("nick"), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("编码{}失败", data.getString("nick"));
                continue;
            }
            Map<String, String> attr = new HashMap<>();
            attr.put("face", data.getString("face"));
            attr.put("distance", data.getString("distance"));
            attr.put("age", data.getString("age"));
            attr.put("province", data.getString("province"));
            attr.put("gender", data.getString("gender"));
            attr.put("headimg", data.getString("url"));
            attr.put("stat", data.getString("stat"));
            attr.put("country", data.getString("country"));
            attr.put("city", data.getString("city"));
            parseResult.setAttr(attr);

            list.add(parseResult);
        }
        return list;
    }

    public static void main(String[]args){
        QQUserAnalyzer analyzer = new QQUserAnalyzer();
        List<ParseResult> list = analyzer.parse("{\"retcode\":0,\"result\":{\"buddy\":{\"count\":20,\"page\":1,\"info_list\":[{\"face\":0,\"distance\":0,\"nick\":\"潇洒哥\",\"age\":22,\"province\":\"山西\",\"gender\":1,\"uin\":1904582304,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=sa2wclSU8AFTJrJQ2178NA\\u0026s=100\\u0026t=1490886761\",\"stat\":1,\"country\":\"中国\",\"city\":\"临汾\"},{\"face\":0,\"distance\":0,\"nick\":\"ジ蓝瘦香菇づ\",\"age\":21,\"province\":\"山西\",\"gender\":1,\"uin\":1975034016,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=bOGbiaJdYRG6c5v2wp9hWxg\\u0026s=100\\u0026t=1485364133\",\"stat\":1,\"country\":\"中国\",\"city\":\"运城\"},{\"face\":0,\"distance\":0,\"nick\":\"安好\",\"age\":22,\"province\":\"山西\",\"gender\":1,\"uin\":377858464,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=8DnVdteYQKPQlOF7C4UyCQ\\u0026s=100\\u0026t=1509583529\",\"stat\":1,\"country\":\"中国\",\"city\":\"吕梁\"},{\"face\":0,\"distance\":0,\"nick\":\"淡T_T漠\",\"age\":18,\"province\":\"山西\",\"gender\":1,\"uin\":2062205600,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=icVPneuicZg301RiczkhkkUxA\\u0026s=100\\u0026t=1483330000\",\"stat\":1,\"country\":\"中国\",\"city\":\"临汾\"},{\"face\":0,\"distance\":0,\"nick\":\"(☆_☆)\",\"age\":22,\"province\":\"山西\",\"gender\":1,\"uin\":1178452896,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=4vARPTiaiaiavkb2ickJrkRQuw\\u0026s=100\\u0026t=1483383602\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"り惜、右眸\",\"age\":22,\"province\":\"山西\",\"gender\":1,\"uin\":956356768,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=mL1XBwBk1pyCJYkRyxA0jQ\\u0026s=100\\u0026t=1483364473\",\"stat\":1,\"country\":\"中国\",\"city\":\"临汾\"},{\"face\":0,\"distance\":0,\"nick\":\"一切安好 　 ζ\",\"age\":22,\"province\":\"山西\",\"gender\":1,\"uin\":1048832928,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=vJq7gYRSWzBp9Cb7UQ3RMQ\\u0026s=100\\u0026t=1483372555\",\"stat\":1,\"country\":\"中国\",\"city\":\"忻州\"},{\"face\":0,\"distance\":0,\"nick\":\"浅\",\"age\":22,\"province\":\"山西\",\"gender\":1,\"uin\":406515616,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=615JY6nQI6fvXnXG180icIw\\u0026s=100\\u0026t=1483316363\",\"stat\":1,\"country\":\"中国\",\"city\":\"阳泉\"},{\"face\":0,\"distance\":0,\"nick\":\"小专\",\"age\":20,\"province\":\"山西\",\"gender\":1,\"uin\":2017195168,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=ibYMXC7jWH8dWM8UqrFoGhQ\\u0026s=100\\u0026t=1497089743\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"李神病\",\"age\":21,\"province\":\"山西\",\"gender\":1,\"uin\":778699425,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=xegyZd2MoVflHkTb3MTGMw\\u0026s=100\\u0026t=1499088074\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"\uD83D\uDC36\",\"age\":18,\"province\":\"山西\",\"gender\":1,\"uin\":2213872545,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=3kyICiapTvgBrXR7CSWIuYA\\u0026s=100\\u0026t=1503378819\",\"stat\":1,\"country\":\"中国\",\"city\":\"运城\"},{\"face\":0,\"distance\":0,\"nick\":\"靳志伟\",\"age\":22,\"province\":\"山西\",\"gender\":1,\"uin\":1620773793,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=ygnuGPJqDiayb583nxxYVXw\\u0026s=100\\u0026t=1483292103\",\"stat\":1,\"country\":\"中国\",\"city\":\"晋城\"},{\"face\":0,\"distance\":0,\"nick\":\"凡人广告\",\"age\":19,\"province\":\"山西\",\"gender\":1,\"uin\":1723404961,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=ODUJlibWwPPFxMmBSWIhdPw\\u0026s=100\\u0026t=1483300819\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"戒不掉の思念\",\"age\":20,\"province\":\"山西\",\"gender\":1,\"uin\":2247562401,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=Uzd6icPfLuewjkyrSicbJXoQ\\u0026s=100\\u0026t=1509571020\",\"stat\":1,\"country\":\"中国\",\"city\":\"运城\"},{\"face\":0,\"distance\":0,\"nick\":\"二三。\",\"age\":20,\"province\":\"山西\",\"gender\":1,\"uin\":1584407969,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=52f7SdvOSsGEibs1kZUp1Aw\\u0026s=100\\u0026t=1484486715\",\"stat\":1,\"country\":\"中国\",\"city\":\"运城\"},{\"face\":0,\"distance\":0,\"nick\":\"堕世天辉\",\"age\":21,\"province\":\"山西\",\"gender\":1,\"uin\":1016538785,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=Ya3PXbiaich2shTeSTiccwy5Q\\u0026s=100\\u0026t=1486963431\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"\uD83C\uDF35一笑而过\",\"age\":19,\"province\":\"山西\",\"gender\":1,\"uin\":2322278817,\"wpa_ck\":\"tencent://message/?Menu=yes\\u0026uin=2322278817\\u0026Site=qq\\u0026Service=201\\u0026SigT=bc436cd5f6b2dcf5141ef5ad1e106ba721a51584f0269d4999ee3d6813e0848756b8888bf6cd66b1bf9eaaaaccf9b465\\u0026SigU=e1971a58654a3d5fd311e4d6bdd4530cd9b0b0461335ae4e71e214a888cd6a76a5c372eb968754c4\",\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=VxsMejWtvbMh3icvNjiab4zw\\u0026s=100\\u0026t=1499547387\",\"stat\":1,\"country\":\"中国\",\"city\":\"大同\"},{\"face\":0,\"distance\":0,\"nick\":\"Amamiya shiki\",\"age\":21,\"province\":\"山西\",\"gender\":1,\"uin\":811608993,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=zIVz6ickqWibjXKJTbaAHMgg\\u0026s=100\\u0026t=1491329943\",\"stat\":1,\"country\":\"中国\",\"city\":\"长治\"},{\"face\":0,\"distance\":0,\"nick\":\"奥利奥\uD83C\uDF69\",\"age\":21,\"province\":\"山西\",\"gender\":1,\"uin\":428223393,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=GJ74ialqeLEB9ujJQFh627Q\\u0026s=100\\u0026t=1483318248\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"土豆你个大地瓜\",\"age\":21,\"province\":\"山西\",\"gender\":1,\"uin\":471282337,\"stat\":1,\"country\":\"中国\",\"city\":\"朔州\"}],\"totalnum\":18411,\"sessionid\":1,\"online\":1,\"endflag\":0},\"sret\":0}}");
        StringBuffer sb = new StringBuffer();
        for(ParseResult parseResult:list){
            sb.append(parseResult.getLink()).append("@qq.com;");
        }
        System.out.println(sb.toString());
    }
}
