/**
 * Created by pepple on 2017/4/21.
 */
Ext.define("My.HKStock",{
    selectIndex : 0,
    activeIndex : 0,
    url : 'http://hq.sinajs.cn/?_dc=',
    imgurl : 'http://image.sinajs.cn/newchart/hk_stock',
    imgtype : ["min","daily","weekly","monthly"],
    interval : 3000,
    elements : [],
    renderTpl : [
        '<div style="margin: 20px 0 0 20px;">',
        '<div id="op">',
        '<a id="stopButton">停止刷新</a>',
        '<span id="updatetime"></span>',
        '</div>',
        '<div class="clear"></div>',
        '<table id="stocklist">',
        '<tr>',
            '<th>代码</th><th>名称</th><th>最新价</th><th>涨跌额</th><th>涨跌幅</th>',
            '<th>买入</th><th>卖出</th><th>昨收</th><th>今开</th><th>最高</th>',
            '<th>最低</th><th>成交量/万</th><th>成交额/万</th>',
        '</tr>',
        '<tpl for=".">',
        '<tr class="">',
            '<td class="center">{.}</td><td class="center"></td>',
            '<td></td><td></td><td></td><td></td><td></td><td></td><td></td>',
            '<td></td><td></td><td></td><td></td>',
        '</tr>',
        '</tpl>',
        '</table><br/><br/>',
        '<div style="width: 700px;text-align: center">',
        '<div id="tabs">',
            '<ul>',
                '<li class="active">分时图</li>',
                '<li>日K线</li>',
                '<li>周K线</li>',
                '<li>月K线</li>',
            '</ul>',
        '</div>',
        '<div id="tabContent">',
            '<img id="img1" width="545" border="0" src=""/>',
        '<div>',
        '</div>'
    ],
    constructor : function(config) {
        var me = this,
        config = config || {};
        code = config["code"];
        if(code) {
            if(Ext.isString(code)) {
                me.code = [code];
            }
            else if(Ext.isArray(code)) {
                me.code = code;
            }
            else if(Ext.isIterable(code)) {
                me.code = Ext.Array.toArray(code);
            }
            else {
                alert("请输入正确的股票代码! ");
                return ;
            }
            if(config["interval"]) {
                me.interval = config["interval"];
            }
            me.task = {
                run : me.getStock,
                interval : me.interval,
                scope : me
            };
            this.render();
        }
        else {
            alert("请输入要查看的股票代码! ");
        }
    },
    render : function() {
        var me = this,el,childs;
        me.tpl = Ext.create('Ext.XTemplate',me.renderTpl);
        me.el = me.tpl.overwrite(Ext.getBody(),me.code);
        childs = Ext.getDom("stocklist").childNodes[0].childNodes;
        for(var i=1;ln=childs.length,i<ln;i++) {
            me.elements.push(childs[i]);
        }
        me.tabs = Ext.select("#tabs li",true);
        me.button = Ext.getDom("stopButton");
        me.updatetime = Ext.getDom("updatetime");
        me.image = Ext.getDom("img1");
        me.rowSelect(0);
        Ext.create("Ext.util.ClickRepeater",me.button,{
            pressedCls : 'pressed',
            listeners : {
                click : me.buttonClick,
                scope : me
            }
        });
        Ext.fly("stocklist").on("click",me.listClick,me);
        Ext.fly("tabs").on("click",me.tabClick,me);
        me.keynav = Ext.create("Ext.util.KeyNav",Ext.getDoc(),{
            up : me.onKeyUp,
            down : me.onKeyDown,
            left : me.onKeyLeft,
            right : me.onKeyRight,
            scope : me
        });
        me.TaskManager = new Ext.util.TaskRunner();
        me.TaskManager.start(me.task);
    },
    buttonClick : function () {
        var me = this;
        el = me.button;
        if(el.innerHTML == "停止刷新") {
            el.innerHTML = "开始刷新";
            me.TaskManager.stop(me.task);
        }
        else {
            el.innerHTML = "停止刷新";
            me.TaskManager.start(me.task);
        }
    },
    listClick : function (e, el) {
        var me = this, t = me.elements;
        for(var i= t.length-1;i>=0;i--) {
            if(el.parentNode == t[i]) {
                me.rowSelect(i-me.selectIndex);
            }
        }
    },
    tabClick : function(e,el) {
        var me = this, t = me.tabs.elements;
        for(var i= t.length-1;i>=0;i--) {
            if(el == t[i].dom) {
                me.tabSelect(i-me.activeIndex);
            }
        }
    },
    getStock : function() {
        var me = this,oldscript = Ext.get("dynscript");
        if(oldscript) {
            oldscript.remove();
        }
        var script = document.createElement('script');
        script.setAttribute("type","text/javascript");
        script.setAttribute("src",me.url + (new Date().getTime()) + "&list=hk" + me.code.join(",hk"));
        script.setAttribute("id","dynscript");
        var cb = Ext.Function.bind(me.callback,me);
        script.onload = cb;
        script.onreadystatechange = function() {
            if(this.readyState == "complete") {
                cb();
            }
        }
        Ext.getHead().appendChild(script);
    },
    callback : function() {
        var me = this;
        for(var i=me.code.length-1;i>=0;i--) {
            var code = me.code[i];
            var v = window["hq_str_hk" + code];
            if(v) {
                vv = v.split(",");
                var el = me.elements[i].childNodes;
                el[1].innerHTML = vv[1];
                el[2].innerHTML = vv[6];
                el[3].innerHTML = vv[7];
                el[4].innerHTML = vv[8] + "%";
                el[5].innerHTML = vv[9];
                el[6].innerHTML = vv[10];
                el[7].innerHTML = vv[3];
                el[8].innerHTML = vv[4];
                el[9].innerHTML = vv[4];
                el[10].innerHTML = vv[5];
                el[11].innerHTML = vv[12] / 10000;
                el[12].innerHTML = vv[11] / 10000;
                var cls = vv[7].substr(0,1) == "-" ? "red" : (parseFloat(vv[7]) > 0 ? "green" : "");
                me.elements[i].style.color = cls;
            }
        }
        me.updatetime.innerHTML = "刷新时间: " + Ext.Date.format(new Date(), "Y-m-d H:i:s");
        me.updateImage();
    },
    updateImage : function () {
        var me = this;
        me.image.src = [me.imgurl,me.imgtype[me.activeIndex],me.code[me.selectIndex] + ".gif?_dc="
        + Ext.Date.format(new Date(), "Y-m-d H:i:s")].join("/");
    },
    onKeyUp : function () {
        var me = this;
        me.rowSelect(-1);
    },
    onKeyDown : function () {
        var me = this;
        me.rowSelect(1);
    },
    onKeyLeft : function () {
        var me = this;
        me.tabSelect(-1);
    },
    onKeyRight : function() {
        var me = this;
        me.tabSelect(1);
    },
    rowSelect : function(v) {
        var me = this;
        me.elements[me.selectIndex].className = "";
        me.selectIndex = me.selectIndex + v;
        if(me.selectIndex < 0) {
            me.selectIndex = me.code.length - 1;
        }
        else if (me.selectIndex > (me.code.length-1)) {
            me.selectIndex = 0;
        }
        me.elements[me.selectIndex].className = "selected";
        me.updateImage();
    },
    tabSelect : function(v) {
        var me = this, tabs = me.tabs.elements;
        tabs[me.activeIndex].removeCls("active");
        me.activeIndex = me.activeIndex + v;
        if(me.activeIndex < 0) {
            me.activeIndex = 3;
        }
        else if(me.activeIndex > 3) {
            me.activeIndex = 0;
        }
        tabs[me.activeIndex].radioCls("active");
        me.updateImage();
    }
});