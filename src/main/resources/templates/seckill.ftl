<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${goods.title}</title>
    <link href="./layui/css/layui.css" rel="stylesheet">
    <script src="./layui/layui.all.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/limonte-sweetalert2/7.21.1/sweetalert2.all.js"></script>
    <style>
        .bb-info li {
            margin-top: 20px;
        }

        .bb-description img {
            width: 100%;
        }

        .bb-param td:first-child {
            background-color: #eeeeee;
        }
        .layui-this a{
            font-size: 18px;
        }
    </style>
</head>
<body style="background-color: #eeeeee">
<ul class="layui-nav layui-bg-green">
    <div class="layui-container">
        <li class="layui-nav-item" style="background-color: orange;margin-left: -14px"><a href="javascript:void(0)" style="font-size: 18px;color: white">宝宝团商城</a></li>
        <li class="layui-nav-item layui-this"><a href="">宝宝团</a></li>
        <li class="layui-nav-item"><a href="">全球购</a></li>
        <li class="layui-nav-item"><a href="">旗舰店</a></li>
        <li class="layui-nav-item"><a href="">宝宝社区</a></li>
    </div>
</ul>

<div class="layui-container" style="padding: 10px;border: 1px solid #ccc;background-color:white">
    <span class="layui-breadcrumb" style="visibility: visible;">
      <a href="/">首页</a><span lay-separator="">/</span>
      <a><cite>${goods.title}</cite></a>
    </span>
    <div class="layui-row" style="padding-top: 10px">
        <div class="layui-col-xs4">
            <div class="layui-carousel" id="test1">
                <div carousel-item>
                    <#list goodsCovers as gc >
                        <div>
                            <img src="${gc.gcPicUrl}" style="width:100%">
                        </div>
                    </#list>

                </div>
            </div>
        </div>
        <div class="layui-col-xs8" style="padding: 10px">
            <ul class="bb-info">
				<li class="layui-bg-red" style="padding:10px;line-height: 30px">
                    <h2>活动结束倒计时<span id="ds">0天4时39分59秒</span></h2>
                </li>
				
                <li>
                    <h2>${goods.title}</h2>
                </li>

                <li class="layui-bg-green" style="padding:10px;line-height: 30px">
                    ${goods.subTitle}
                </li>

                <li style="color: darkgrey">
                    <span>宝宝团价：</span><span style="font-size: 26px;color: orange;font-weight: bold">¥${promotionSeckill.currentPrice}</span><span
                        style="text-decoration: line-through">¥${goods.originalCost}</span>
					<span>,库存只有<span style="font-size: 26px;color: orange;font-weight: bold">${promotionSeckill.psCount}</span>件啦,每人限购一件</span>
                </li>
				<li>
                    
                </li>
                
                <li>
                    <#if promotionSeckill.status == 1>
                        <button class="layui-btn layui-btn-normal layui-bg-red" id="actionId">立即抢购</button>
                    </#if>
                    <#if promotionSeckill.status == 0>
                        <button class="layui-btn layui-btn-disabled " disabled="disabled" id="actionId">立即抢购</button>
                        <h3>活动尚未开始</h3>
                    </#if>
                    <#if promotionSeckill.status == 2>
                        <button class="layui-btn layui-btn-disabled " disabled="disabled" id="actionId">立即抢购</button>
                        <h3>活动已结束</h3>
                    </#if>


                </li>
            </ul>
        </div>
    </div>

    <div class="layui-row">
        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
            <ul class="layui-tab-title">
                <li class="layui-this">产品详情</li>
                <li>产品参数</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show bb-description layui-col-xs8 layui-col-xs-offset2">
                    <#list goodsDetails as gd>
                        <img src="${gd.gdPicUrl}">
                    </#list>



                </div>
                <div class="layui-tab-item layui-col-xs8 layui-col-xs-offset2">
                    <table class="layui-table bb-param" lay-size="lg">
                        <colgroup>
                            <col width="30%">
                            <col width="70%">
                        </colgroup>
                        <tbody>
                        <#if params??>
                            <#list params as param>
                                <tr>
                                    <td>${param.gpParamName}</td>
                                    <td>${param.gpParamValue}</td>
                                </tr>
                            </#list>
                        </#if>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    //由于模块都一次性加载，因此不用执行 layui.use() 来加载对应模块，直接使用即可：
    ;!function () {
        var layer = layui.layer
            , form = layui.form;

    }();
</script>
<script>
    layui.use('carousel', function () {
        var carousel = layui.carousel;
        //建造实例
        carousel.render({
            elem: '#test1'
            , width: '380px' //设置容器宽度
            , height: '380px'
            , arrow: 'hover' //始终显示箭头
            , anim: 'fade' //切换动画方式
        });

        var $ = layui.$;
        $("#actionId").click(function (){
           $.getJSON("/seckill?psId=${promotionSeckill.psId}&userId=u03",function (json){
               if(json.code == "0"){
                   alert("恭喜你，恭喜得到抢购的资格，快去下单把，")
                   var orderNo = json.data.orderNo;
                   console.info(orderNo);
                   swal({
                       title:"正在创建订单，请稍后。。。",
                       timer:3000,
                       showConfirmButton: false,
                       type:"info"
                   }).then(function (){
                       window.location.href = "/checkorder?orderNo=" + orderNo;
                       alert("抢购成功")
                   })
               }else {
                   alert(json.message);
               }
           });
        });

    });
</script>

<script>
layui.use('util', function(){
  var util = layui.util;
  
  //示例
  var endTime = new Date().getTime() + (3600 * 1000 * 24) //假设为结束日期
  ,serverTime = new Date().getTime(); //假设为当前服务器时间，这里采用的是本地时间，实际使用一般是取服务端的
   
  util.countdown(endTime, serverTime, function(date, serverTime, timer){
    var str = date[0] + '天' + date[1] + '时' +  date[2] + '分' + date[3] + '秒';
    layui.$('#ds').html(str);
  });
});
</script>


</body>
</html>
