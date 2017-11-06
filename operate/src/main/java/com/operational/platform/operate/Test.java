package com.operational.platform.operate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.operational.platform.dbservice.model.AttackTask;
import com.operational.platform.dbservice.service.AttackTaskService;
import com.operational.platform.operate.util.SpringContext;

/**
 * Created by lihuajun on 2017/11/6.
 */
public class Test {

    public static void main(String[]args){

        SpringContext.init("classpath:spring/spring.xml");

        AttackTaskService attackTaskService =(AttackTaskService) SpringContext.getContext().getBean("attackTaskService");


        String json = "{\"retcode\":0,\"result\":{\"buddy\":{\"count\":20,\"page\":1,\"info_list\":[{\"face\":0,\"distance\":0,\"nick\":\"传禧龙广告设计A组\",\"age\":22,\"province\":\"山西\",\"gender\":1,\"uin\":2679187689,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=nQWLVXX25uPxR1ibPElLyvg\\u0026s=100\\u0026t=549\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"\uD83D\uDE0E\",\"age\":21,\"province\":\"山西\",\"gender\":1,\"uin\":358112233,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=iaIoRiaXFqMKb85ulCXk5kdw\\u0026s=100\\u0026t=1487775958\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"福网产品专员李筱\",\"age\":22,\"province\":\"山西\",\"gender\":1,\"uin\":609992425,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=tL18JCBj487pFPcwb4Ria6Q\\u0026s=100\\u0026t=1483334150\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"ヾ96丶经典つヾ\",\"age\":21,\"province\":\"山西\",\"gender\":1,\"uin\":936388842,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=ibyrrxUcdWicMujZibApaeiawg\\u0026s=100\\u0026t=1505391121\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"juan0008\",\"age\":21,\"province\":\"山西\",\"gender\":1,\"uin\":363871466,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=zXQNto9JgSQCyhTUB5mHGg\\u0026s=100\\u0026t=1501246925\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"Yep\uD83D\uDC7B\",\"age\":22,\"province\":\"山西\",\"gender\":1,\"uin\":20202474,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=LNkRRvQutWRHA8pQnjFkkA\\u0026s=100\\u0026t=1504705010\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"就闹  你能耐我何i\",\"age\":20,\"province\":\"山西\",\"gender\":1,\"uin\":905607146,\"wpa_ck\":\"tencent://message/?Menu=yes\\u0026uin=905607146\\u0026Site=qq\\u0026Service=201\\u0026SigT=ee8adc437e545faa1e86d2429b4041944381c9b9fb15841994481254a6504ae61df4e9ab5cd6fe9cc5cf216aaf3009a2\\u0026SigU=e5084369b6bd210a2213d73f8671d535b7656de051623713b25cf57fb26c82c580d702eaf51eeb82\",\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=mbe8KN0FZvhPzPMn48H1oQ\\u0026s=100\\u0026t=1498011652\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"☞南邵☜\",\"age\":18,\"province\":\"山西\",\"gender\":1,\"uin\":2760872170,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=9wupnhQmjRuT6ibbibzMayhg\\u0026s=100\\u0026t=1501811399\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"TRE男装\",\"age\":18,\"province\":\"山西\",\"gender\":1,\"uin\":3255145706,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=V2Tx5ZXBaPfFg3dW3F6T4A\\u0026s=100\\u0026t=1488771335\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"风流倜傥王大大\",\"age\":21,\"province\":\"山西\",\"gender\":1,\"uin\":2817171946,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=szHzsO5mhy8PIuwiaTqoZiaA\\u0026s=100\\u0026t=1501917754\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"浮夸。\",\"age\":22,\"province\":\"山西\",\"gender\":1,\"uin\":974564586,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=LTIznicRxPzAaenvmWhp1Ig\\u0026s=100\\u0026t=1501048619\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"北海故里\",\"age\":20,\"province\":\"山西\",\"gender\":1,\"uin\":1443745002,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=tlMQMfWcoDgyOAAMF6IYtA\\u0026s=100\\u0026t=1496633185\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"昌盛以纯店\",\"age\":21,\"province\":\"山西\",\"gender\":1,\"uin\":1663762154,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=yJ4tnkr5JITFiaUvN6kIznA\\u0026s=100\\u0026t=588\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"......\",\"age\":21,\"province\":\"山西\",\"gender\":1,\"uin\":2318205675,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=JvfZRHBWWXE1KhygjdK9LQ\\u0026s=100\\u0026t=1504080412\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"忆\",\"age\":21,\"province\":\"山西\",\"gender\":1,\"uin\":204678123,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=KHAHPlJkwkjEyIkdezxl2g\\u0026s=100\\u0026t=1483299178\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"白天恋情\",\"age\":19,\"province\":\"山西\",\"gender\":1,\"uin\":1329477867,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=jkXrx0xUpaOYzOErb6610Q\\u0026s=100\\u0026t=1486351023\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"迎泽站信息\",\"age\":22,\"province\":\"山西\",\"gender\":1,\"uin\":2468713707,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=LU3dibKqaBsuZSZlEjtGzSw\\u0026s=100\\u0026t=594\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"海阔天空\",\"age\":18,\"province\":\"山西\",\"gender\":1,\"uin\":1536559596,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=uBFxDAWqWcKPxC1Zy2CJGQ\\u0026s=100\\u0026t=1483284883\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"自强$不息\",\"age\":22,\"province\":\"山西\",\"gender\":1,\"uin\":1129846252,\"url\":\"http://thirdqq.qlogo.cn/g?b=sdk\\u0026k=uaLAnGvdzNEpOu7qOXJNgg\\u0026s=100\\u0026t=1483379468\",\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"},{\"face\":0,\"distance\":0,\"nick\":\"哈哈！！\",\"age\":19,\"province\":\"山西\",\"gender\":1,\"uin\":875399660,\"stat\":1,\"country\":\"中国\",\"city\":\"太原\"}],\"totalnum\":4683,\"sessionid\":1,\"online\":1,\"endflag\":0},\"sret\":0}}";

        JSONArray jsonArray = JSONObject.parseObject(json).getJSONObject("result").getJSONObject("buddy").getJSONArray("info_list");

        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            AttackTask attackTask = new AttackTask();
            attackTask.setBelong("addQQ");
            attackTask.setStatus(0);
            attackTask.setData(jsonObject.getString("uin"));

            attackTaskService.save(attackTask);
        }

    }

}
