(function(){
'use strict';var ZF,$F,cG,sG,wG,zG,AG,CG,EG,uG;ZF=function(a){return[$APP.p.o(a.substring(0,6)),"....",$APP.p.o(a.substring(34))].join("")};
$F=function(a){var b=document.createElement("textarea"),c=document.activeElement,d=function(){var e=window.pageYOffset;return $APP.m(e)?e:document.documentElement.scrollTop}();b.style={position:"absolute",left:"-9999px",top:[$APP.p.o(d),"px"].join(""),fontSize:"12pt",border:"0",padding:"0",margin:"0"};b.value=a;b.addEventListener("focus",function(){return window.scrollTo(0,d)});document.body.appendChild(b);b.setSelectionRange(0,b.value.length);b.focus();document.execCommand("copy");b.blur();$APP.m(c)&&
c.focus();window.getSelection().removeAllRanges();window.document.body.removeChild(b)};$APP.aG=function(){return new $APP.P(null,1,5,$APP.Q,[new $APP.l(null,2,[$APP.Up,function(){$APP.Qq(new $APP.P(null,1,5,$APP.Q,[$APP.oC],null));$APP.Qq(new $APP.P(null,1,5,$APP.Q,[$APP.Uy],null));$APP.Qq(new $APP.P(null,1,5,$APP.Q,[$APP.rA],null));$APP.Qq(new $APP.P(null,1,5,$APP.Q,[$APP.Vv],null));return $APP.Qq(new $APP.P(null,1,5,$APP.Q,[$APP.Vx],null))},$APP.iA,$APP.li],null)],null)};
cG=function(){var a=$APP.am.o,b=localStorage.getItem("user-avatar");a=a.call($APP.am,$APP.m(b)?b:"knight");a:switch(a instanceof $APP.Ci?a.qa:null){case "knight":a="/images/avatar-knight.png";break a;default:a="/images/avatar-lord.png"}return new $APP.P(null,2,5,$APP.Q,[bG,new $APP.P(null,2,5,$APP.Q,[$APP.$x,new $APP.l(null,1,[$APP.hD,a],null)],null)],null)};
sG=function(){var a=$APP.Vf($APP.Kr(new $APP.P(null,1,5,$APP.Q,[dG],null)));a=$APP.gj(a);var b=$APP.H.j(a,$APP.Jx),c=a=$APP.Q,d=$APP.Q;$APP.nn("fgl.app.views.dashboard",new $APP.l(null,3,[eG,fG,$APP.nz,b,$APP.Mv,67],null));var e=$APP.m(b)?ZF(b):b;return new $APP.P(null,3,5,a,[gG,new $APP.P(null,3,5,c,[hG,new $APP.P(null,2,5,d,[iG,e],null),new $APP.P(null,3,5,$APP.Q,[jG,new $APP.P(null,3,5,$APP.Q,[$APP.uu,new $APP.l(null,3,[$APP.vA,function(){return open($APP.xn($APP.D(["/address/",b])))},$APP.pu,
$APP.PD,$APP.vp,"mr-4"],null),new $APP.P(null,3,5,$APP.Q,[kG,new $APP.P(null,2,5,$APP.Q,[lG,new $APP.l(null,1,[$APP.hD,"/images/share.png"],null)],null),"View on explorer"],null)],null),new $APP.P(null,3,5,$APP.Q,[$APP.uu,new $APP.l(null,2,[$APP.pu,$APP.PD,$APP.vA,function(){$F(b);return $APP.Qq(new $APP.P(null,2,5,$APP.Q,[$APP.Et,new $APP.l(null,2,[$APP.It,"Copied!",$APP.$C,!0],null)],null))}],null),new $APP.P(null,3,5,$APP.Q,[kG,new $APP.P(null,2,5,$APP.Q,[mG,new $APP.l(null,1,[$APP.hD,"/images/copy.png"],
null)],null),"Copy Address"],null)],null)],null)],null),new $APP.P(null,3,5,$APP.Q,[nG,new $APP.P(null,2,5,$APP.Q,[oG,new $APP.l(null,1,[$APP.hD,"/images/k1.png"],null)],null),new $APP.P(null,3,5,$APP.Q,[$APP.nw,new $APP.P(null,3,5,$APP.Q,[pG,new $APP.P(null,2,5,$APP.Q,[qG,"ASTAS"],null),new $APP.P(null,2,5,$APP.Q,[rG,"ROLE:XXXXX"],null)],null),new $APP.P(null,3,5,$APP.Q,[$APP.uu,new $APP.l(null,2,[$APP.vA,function(){return open($APP.xn($APP.D(["/address/",b])))},$APP.pu,$APP.PD],null),new $APP.P(null,
3,5,$APP.Q,[kG,new $APP.P(null,2,5,$APP.Q,[lG,new $APP.l(null,1,[$APP.hD,"/images/share.png"],null)],null),"View on explorer"],null)],null)],null)],null)],null)};
wG=function(){var a=$APP.Vf($APP.Kr(new $APP.P(null,1,5,$APP.Q,[dG],null))),b=$APP.gj(a);a=$APP.H.j(b,$APP.jx);var c=$APP.H.j(b,$APP.Yv),d=$APP.H.j(b,$APP.Mz);b=$APP.H.j(b,$APP.my);return new $APP.P(null,5,5,$APP.Q,[tG,new $APP.P(null,3,5,$APP.Q,[$APP.wF,new $APP.l(null,1,[$APP.vp,"w-32"],null),new $APP.P(null,3,5,$APP.Q,[$APP.tx,new $APP.P(null,3,5,$APP.Q,[$APP.KF,"3rem",new $APP.l(null,1,[$APP.fu,new $APP.l(null,1,[$APP.cw,"-1.4rem 0 -1.4rem -1.4rem"],null)],null)],null),new $APP.P(null,3,5,$APP.Q,
[$APP.IF,c,new $APP.l(null,1,[$APP.vp,"mr-2"],null)],null)],null)],null),new $APP.P(null,3,5,$APP.Q,[$APP.wF,new $APP.l(null,1,[$APP.vp,"w-32"],null),new $APP.P(null,3,5,$APP.Q,[$APP.tx,new $APP.P(null,3,5,$APP.Q,[$APP.JF,"3rem",new $APP.l(null,1,[$APP.fu,new $APP.l(null,1,[$APP.cw,"-1.4rem 0 -1.4rem -1.4rem"],null)],null)],null),new $APP.P(null,3,5,$APP.Q,[$APP.IF,a,new $APP.l(null,1,[$APP.vp,"mr-2"],null)],null)],null)],null),new $APP.P(null,3,5,$APP.Q,[$APP.wF,new $APP.l(null,1,[$APP.vp,"w-32"],
null),new $APP.P(null,3,5,$APP.Q,[$APP.tx,new $APP.P(null,3,5,$APP.Q,[uG,"3rem",new $APP.l(null,1,[$APP.fu,new $APP.l(null,1,[$APP.cw,"-1.4rem 0 -1.4rem -1.4rem"],null)],null)],null),new $APP.P(null,3,5,$APP.Q,[$APP.IF,d,new $APP.l(null,1,[$APP.vp,"mr-2"],null)],null)],null)],null),new $APP.P(null,3,5,$APP.Q,[$APP.wF,new $APP.l(null,1,[$APP.vp,"w-32"],null),new $APP.P(null,3,5,$APP.Q,[$APP.tx,new $APP.P(null,3,5,$APP.Q,[vG,"3rem",new $APP.l(null,1,[$APP.fu,new $APP.l(null,1,[$APP.cw,"-1.4rem 0 -1.4rem -1.4rem"],
null)],null)],null),new $APP.P(null,3,5,$APP.Q,[$APP.IF,b,new $APP.l(null,1,[$APP.vp,"mr-2"],null)],null)],null)],null)],null)};zG=function(){return new $APP.P(null,3,5,$APP.Q,[xG,new $APP.P(null,1,5,$APP.Q,[cG],null),new $APP.P(null,3,5,$APP.Q,[yG,new $APP.P(null,1,5,$APP.Q,[sG],null),new $APP.P(null,1,5,$APP.Q,[wG],null)],null)],null)};AG=function(a){return 10>a?["0",$APP.p.o(a)].join(""):a};
CG=function(a){var b=$APP.G(a,0,null);a=$APP.G(a,1,null);var c=$APP.gj(a);a=$APP.H.j(c,$APP.vu);var d=$APP.H.j(c,$APP.wu);c=$APP.Q;var e=new $APP.l(null,1,[$APP.fu,new $APP.l(null,1,[$APP.Zw,"1fr 3fr 1fr"],null)],null),f=$APP.Q;d=[$APP.p.o(d.getFullYear()),".",$APP.p.o(AG(d.getMonth()+1)),".",$APP.p.o(AG(d.getDate()))].join("");return $APP.Gh(new $APP.P(null,5,5,c,[BG,e,new $APP.P(null,2,5,f,[$APP.ZA,d],null),new $APP.P(null,3,5,$APP.Q,[kG,new $APP.P(null,2,5,$APP.Q,[$APP.KF,"1.5rem"],null),new $APP.P(null,
2,5,$APP.Q,[$APP.ZA,"Gold"],null)],null),new $APP.P(null,2,5,$APP.Q,[$APP.IF,a],null)],null),new $APP.l(null,1,[$APP.Dn,b],null))};
EG=function(){return new $APP.P(null,3,5,$APP.Q,[DG,new $APP.l(null,1,[$APP.fu,new $APP.l(null,1,[$APP.hu,"calc(100% - 180px - 7.5rem)"],null)],null),new $APP.P(null,4,5,$APP.Q,[$APP.Uv,new $APP.l(null,1,[$APP.fu,new $APP.l(null,3,[$APP.hu,"calc(100% - 2.25rem)",$APP.kz,"2px ridge #50929e33",$APP.iz,"linear-gradient(\n    rgba(163, 218, 226, 0.1),\n    rgba(163, 218, 226, 0.05)\n  )"],null)],null),new $APP.P(null,5,5,$APP.Q,[$APP.JC,new $APP.l(null,1,[$APP.fu,new $APP.l(null,1,[$APP.Zw,"1fr 3fr 1fr"],
null)],null),new $APP.P(null,2,5,$APP.Q,[$APP.ZA,"DATE"],null),new $APP.P(null,2,5,$APP.Q,[$APP.ZA,"ASSET"],null),new $APP.P(null,2,5,$APP.Q,[$APP.ZA,"sGold locked"],null)],null),function(){var a=$APP.Vf($APP.Kr(new $APP.P(null,1,5,$APP.Q,[dG],null)));a=$APP.gj(a);a=$APP.H.j(a,$APP.Mx);return new $APP.P(null,3,5,$APP.Q,[$APP.sj,new $APP.P(null,2,5,$APP.Q,[$APP.CD,new $APP.l(null,1,[$APP.fu,new $APP.l(null,1,[$APP.hu,"calc(100% - 1.25rem)"],null)],null)],null),$APP.vj(function(b,c){return new $APP.P(null,
2,5,$APP.Q,[CG,new $APP.P(null,2,5,$APP.Q,[b,c],null)],null)},a)],null)}()],null)],null)};$APP.GG=function(){return new $APP.P(null,3,5,$APP.Q,[FG,new $APP.P(null,1,5,$APP.Q,[zG],null),new $APP.P(null,1,5,$APP.Q,[EG],null)],null)};uG=function uG(a){switch(arguments.length){case 1:return uG.o(arguments[0]);case 2:return uG.j(arguments[0],arguments[1]);default:throw Error(["Invalid arity: ",$APP.p.o(arguments.length)].join(""));}};uG.o=function(a){return uG.j(a,$APP.M)};
uG.j=function(a,b){a=$APP.gf?a:[$APP.p.o(a),"rem"].join("");return new $APP.P(null,2,5,$APP.Q,[$APP.$x,$APP.du($APP.D([b,new $APP.l(null,2,[$APP.fu,new $APP.l(null,2,[$APP.ku,a,$APP.hu,a],null),$APP.hD,"/images/rune-token.png"],null)]))],null)};uG.J=2;
var xG=$APP.S("div.flex.flex-row.justify-between.items-center.p-8"),pG=$APP.S("div.flex.flex-row.items-baseline.mb-4"),jG=$APP.S("div.flex.flex-row.justify-between.items-center"),qG=$APP.S("div.uppercase.text-3xl.fi.mr-4"),mG=$APP.S("img.mr-2.w-4"),lG=$APP.S("img.w-4.mr-2"),FG=$APP.S("div.h-full.w-full"),rG=$APP.S("div.text-xl"),nG=$APP.S("div.flexr"),dG=$APP.T("fgl.app.views.dashboard","data"),oG=$APP.S("img.h-30.mr-4"),tG=$APP.S("div.flex.flex-row.justify-around.items-cednter.bg-C81c6dd33.py-3.rounded-lg.fb"),
iG=$APP.S("div.text-3xl.fi.mb-4"),DG=$APP.S("div.px-6"),fG=new $APP.t(null,"addr","addr",42880790,null),hG=$APP.S("div.mr-8"),bG=$APP.S("div.rounded-full.bg-C25376f.w-40.h-40.mr-8.self-start"),gG=$APP.S("div.flexrs.mb-4"),yG=$APP.S("div.grow"),eG=$APP.S("spy"),kG=$APP.S("span.flexr"),BG=$APP.S("div.grid.justify-items-center.pb-1.border-b.border-C79c5da.pt-1_125rem.fp.font-bold.items-center");$APP.id("dashboard");var vG=function vG(a){switch(arguments.length){case 1:return vG.o(arguments[0]);case 2:return vG.j(arguments[0],arguments[1]);default:throw Error(["Invalid arity: ",$APP.p.o(arguments.length)].join(""));}};vG.o=function(a){return vG.j(a,$APP.M)};
vG.j=function(a,b){a=$APP.gf?a:[$APP.p.o(a),"rem"].join("");return new $APP.P(null,2,5,$APP.Q,[$APP.$x,$APP.du($APP.D([b,new $APP.l(null,2,[$APP.fu,new $APP.l(null,3,[$APP.ku,$APP.un(a,.81818182),$APP.hu,a,$APP.ju,"translateY(5%)"],null),$APP.hD,"/images/landeed-token.png"],null)]))],null)};vG.J=2;$APP.HG={};$APP.Ir(dG,$APP.D([function(a){var b=$APP.gj(a);b=$APP.H.j(b,$APP.Iz);a=$APP.H.j(a,b);var c=$APP.gj(a);a=$APP.H.j(c,$APP.Cx);var d=$APP.H.j(c,$APP.CC),e=$APP.H.j(c,$APP.Hv),f=$APP.H.j(c,$APP.Gy);c=$APP.H.j(c,$APP.aD);return new $APP.l(null,6,[$APP.Jx,b,$APP.Yv,a,$APP.jx,d,$APP.Mz,e,$APP.my,f,$APP.Mx,$APP.xu(c)],null)}]));$APP.m(localStorage.getItem("user-avatar"))||localStorage.setItem("user-avatar","knight");$APP.Se();
}).call(this);