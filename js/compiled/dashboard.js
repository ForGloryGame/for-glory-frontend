(function(){
'use strict';var QG,RG,UG,hH,lH,oH,pH,rH,tH,jH;QG=function(a){return[$APP.p.o(a.substring(0,6)),"....",$APP.p.o(a.substring(34))].join("")};
RG=function(a){var b=document.createElement("textarea"),c=document.activeElement,d=function(){var e=window.pageYOffset;return $APP.m(e)?e:document.documentElement.scrollTop}();b.style={position:"absolute",left:"-9999px",top:[$APP.p.o(d),"px"].join(""),fontSize:"12pt",border:"0",padding:"0",margin:"0"};b.value=a;b.addEventListener("focus",function(){return window.scrollTo(0,d)});document.body.appendChild(b);b.setSelectionRange(0,b.value.length);b.focus();document.execCommand("copy");b.blur();$APP.m(c)&&
c.focus();window.getSelection().removeAllRanges();window.document.body.removeChild(b)};$APP.SG=function(){return new $APP.P(null,1,5,$APP.Q,[new $APP.k(null,2,[$APP.bq,function(){$APP.Yq(new $APP.P(null,1,5,$APP.Q,[$APP.aD],null));$APP.Yq(new $APP.P(null,1,5,$APP.Q,[$APP.Az],null));$APP.Yq(new $APP.P(null,1,5,$APP.Q,[$APP.$A],null));$APP.Yq(new $APP.P(null,1,5,$APP.Q,[$APP.pw],null));return $APP.Yq(new $APP.P(null,1,5,$APP.Q,[$APP.xy],null))},$APP.QA,$APP.ki],null)],null)};
UG=function(){var a=$APP.Zl.o,b=localStorage.getItem("user-avatar");a=a.call($APP.Zl,$APP.m(b)?b:"knight");a:switch(a instanceof $APP.Ai?a.qa:null){case "knight":a="/images/avatar-knight.png";break a;default:a="/images/avatar-lord.png"}return new $APP.P(null,2,5,$APP.Q,[TG,new $APP.P(null,2,5,$APP.Q,[$APP.Cy,new $APP.k(null,1,[$APP.UD,a],null)],null)],null)};
hH=function(){var a=$APP.Uf($APP.Sr(new $APP.P(null,1,5,$APP.Q,[VG],null)));a=$APP.N(a);var b=$APP.H.j(a,$APP.ky);return new $APP.P(null,3,5,$APP.Q,[WG,new $APP.P(null,3,5,$APP.Q,[XG,new $APP.P(null,2,5,$APP.Q,[YG,$APP.m(b)?QG(b):b],null),new $APP.P(null,3,5,$APP.Q,[ZG,new $APP.P(null,3,5,$APP.Q,[$APP.Ot,new $APP.k(null,3,[$APP.St,function(){return open($APP.xt($APP.D(["/address/",b])))},$APP.Jt,$APP.CE,$APP.tp,"mr-4"],null),new $APP.P(null,3,5,$APP.Q,[$G,new $APP.P(null,2,5,$APP.Q,[aH,new $APP.k(null,
1,[$APP.UD,"/images/share.png"],null)],null),"View on explorer"],null)],null),new $APP.P(null,3,5,$APP.Q,[$APP.Ot,new $APP.k(null,2,[$APP.Jt,$APP.CE,$APP.St,function(){RG(b);return $APP.Yq(new $APP.P(null,2,5,$APP.Q,[$APP.PB,new $APP.k(null,2,[$APP.ku,"Copied!",$APP.ND,!0],null)],null))}],null),new $APP.P(null,3,5,$APP.Q,[$G,new $APP.P(null,2,5,$APP.Q,[bH,new $APP.k(null,1,[$APP.UD,"/images/copy.png"],null)],null),"Copy Address"],null)],null)],null)],null),new $APP.P(null,3,5,$APP.Q,[cH,new $APP.P(null,
2,5,$APP.Q,[dH,new $APP.k(null,1,[$APP.UD,"/images/k1.png"],null)],null),new $APP.P(null,3,5,$APP.Q,[$APP.Kw,new $APP.P(null,3,5,$APP.Q,[eH,new $APP.P(null,2,5,$APP.Q,[fH,"ASTAS"],null),new $APP.P(null,2,5,$APP.Q,[gH,"ROLE:XXXXX"],null)],null),new $APP.P(null,3,5,$APP.Q,[$APP.Ot,new $APP.k(null,2,[$APP.St,function(){return open($APP.xt($APP.D(["/address/",b])))},$APP.Jt,$APP.CE],null),new $APP.P(null,3,5,$APP.Q,[$G,new $APP.P(null,2,5,$APP.Q,[aH,new $APP.k(null,1,[$APP.UD,"/images/share.png"],null)],
null),"View on explorer"],null)],null)],null)],null)],null)};
lH=function(){var a=$APP.Uf($APP.Sr(new $APP.P(null,1,5,$APP.Q,[VG],null))),b=$APP.N(a);a=$APP.H.j(b,$APP.Ix);var c=$APP.H.j(b,$APP.sw),d=$APP.H.j(b,$APP.tA);b=$APP.H.j(b,$APP.Qy);return new $APP.P(null,5,5,$APP.Q,[iH,new $APP.P(null,3,5,$APP.Q,[$APP.uG,new $APP.k(null,1,[$APP.tp,"w-32"],null),new $APP.P(null,3,5,$APP.Q,[$APP.Sx,new $APP.P(null,3,5,$APP.Q,[$APP.BG,"3rem",new $APP.k(null,1,[$APP.zt,new $APP.k(null,1,[$APP.yw,"-1.4rem 0 -1.4rem -1.4rem"],null)],null)],null),new $APP.P(null,3,5,$APP.Q,
[$APP.pG,c,new $APP.k(null,1,[$APP.tp,"mr-2"],null)],null)],null)],null),new $APP.P(null,3,5,$APP.Q,[$APP.uG,new $APP.k(null,1,[$APP.tp,"w-32"],null),new $APP.P(null,3,5,$APP.Q,[$APP.Sx,new $APP.P(null,3,5,$APP.Q,[$APP.rG,"3rem",new $APP.k(null,1,[$APP.zt,new $APP.k(null,1,[$APP.yw,"-1.4rem 0 -1.4rem -1.4rem"],null)],null)],null),new $APP.P(null,3,5,$APP.Q,[$APP.pG,a,new $APP.k(null,1,[$APP.tp,"mr-2"],null)],null)],null)],null),new $APP.P(null,3,5,$APP.Q,[$APP.uG,new $APP.k(null,1,[$APP.tp,"w-32"],
null),new $APP.P(null,3,5,$APP.Q,[$APP.Sx,new $APP.P(null,3,5,$APP.Q,[jH,"3rem",new $APP.k(null,1,[$APP.zt,new $APP.k(null,1,[$APP.yw,"-1.4rem 0 -1.4rem -1.4rem"],null)],null)],null),new $APP.P(null,3,5,$APP.Q,[$APP.pG,d,new $APP.k(null,1,[$APP.tp,"mr-2"],null)],null)],null)],null),new $APP.P(null,3,5,$APP.Q,[$APP.uG,new $APP.k(null,1,[$APP.tp,"w-32"],null),new $APP.P(null,3,5,$APP.Q,[$APP.Sx,new $APP.P(null,3,5,$APP.Q,[kH,"3rem",new $APP.k(null,1,[$APP.zt,new $APP.k(null,1,[$APP.yw,"-1.4rem 0 -1.4rem -1.4rem"],
null)],null)],null),new $APP.P(null,3,5,$APP.Q,[$APP.pG,b,new $APP.k(null,1,[$APP.tp,"mr-2"],null)],null)],null)],null)],null)};oH=function(){return new $APP.P(null,3,5,$APP.Q,[mH,new $APP.P(null,1,5,$APP.Q,[UG],null),new $APP.P(null,3,5,$APP.Q,[nH,new $APP.P(null,1,5,$APP.Q,[hH],null),new $APP.P(null,1,5,$APP.Q,[lH],null)],null)],null)};pH=function(a){return 10>a?["0",$APP.p.o(a)].join(""):a};
rH=function(a){var b=$APP.G(a,0,null);a=$APP.G(a,1,null);var c=$APP.N(a);a=$APP.H.j(c,$APP.Lu);var d=$APP.H.j(c,$APP.Mu);c=$APP.Q;var e=new $APP.k(null,1,[$APP.zt,new $APP.k(null,1,[$APP.xx,"1fr 3fr 1fr"],null)],null),f=$APP.Q;d=[$APP.p.o(d.getFullYear()),".",$APP.p.o(pH(d.getMonth()+1)),".",$APP.p.o(pH(d.getDate()))].join("");return $APP.Fh(new $APP.P(null,5,5,c,[qH,e,new $APP.P(null,2,5,f,[$APP.Zt,d],null),new $APP.P(null,3,5,$APP.Q,[$G,new $APP.P(null,2,5,$APP.Q,[$APP.BG,"1.5rem"],null),new $APP.P(null,
2,5,$APP.Q,[$APP.Zt,"Gold"],null)],null),new $APP.P(null,2,5,$APP.Q,[$APP.pG,a],null)],null),new $APP.k(null,1,[$APP.En,b],null))};
tH=function(){return new $APP.P(null,3,5,$APP.Q,[sH,new $APP.k(null,1,[$APP.zt,new $APP.k(null,1,[$APP.Bt,"calc(100% - 180px - 7.5rem)"],null)],null),new $APP.P(null,4,5,$APP.Q,[$APP.ow,new $APP.k(null,1,[$APP.zt,new $APP.k(null,3,[$APP.Bt,"calc(100% - 2.25rem)",$APP.Tz,"2px ridge #50929e33",$APP.Rz,"linear-gradient(\n    rgba(163, 218, 226, 0.1),\n    rgba(163, 218, 226, 0.05)\n  )"],null)],null),new $APP.P(null,5,5,$APP.Q,[$APP.vD,new $APP.k(null,1,[$APP.zt,new $APP.k(null,1,[$APP.xx,"1fr 3fr 1fr"],
null)],null),new $APP.P(null,2,5,$APP.Q,[$APP.Zt,"DATE"],null),new $APP.P(null,2,5,$APP.Q,[$APP.Zt,"ASSET"],null),new $APP.P(null,2,5,$APP.Q,[$APP.Zt,"sGold locked"],null)],null),function(){var a=$APP.Uf($APP.Sr(new $APP.P(null,1,5,$APP.Q,[VG],null)));a=$APP.N(a);a=$APP.H.j(a,$APP.ny);return new $APP.P(null,3,5,$APP.Q,[$APP.pj,new $APP.P(null,2,5,$APP.Q,[$APP.oE,new $APP.k(null,1,[$APP.zt,new $APP.k(null,1,[$APP.Bt,"calc(100% - 1.25rem)"],null)],null)],null),$APP.sj(function(b,c){return new $APP.P(null,
2,5,$APP.Q,[rH,new $APP.P(null,2,5,$APP.Q,[b,c],null)],null)},a)],null)}()],null)],null)};$APP.vH=function(){return new $APP.P(null,3,5,$APP.Q,[uH,new $APP.P(null,1,5,$APP.Q,[oH],null),new $APP.P(null,1,5,$APP.Q,[tH],null)],null)};jH=function jH(a){switch(arguments.length){case 1:return jH.o(arguments[0]);case 2:return jH.j(arguments[0],arguments[1]);default:throw Error(["Invalid arity: ",$APP.p.o(arguments.length)].join(""));}};jH.o=function(a){return jH.j(a,$APP.M)};
jH.j=function(a,b){a=$APP.ef?a:[$APP.p.o(a),"rem"].join("");return new $APP.P(null,2,5,$APP.Q,[$APP.Cy,$APP.yn($APP.D([b,new $APP.k(null,2,[$APP.zt,new $APP.k(null,2,[$APP.Et,a,$APP.Bt,a],null),$APP.UD,"/images/rune-token.png"],null)]))],null)};jH.H=2;
var mH=$APP.S("div.flex.flex-row.justify-between.items-center.p-8"),eH=$APP.S("div.flex.flex-row.items-baseline.mb-4"),ZG=$APP.S("div.flex.flex-row.justify-between.items-center"),fH=$APP.S("div.uppercase.text-3xl.fi.mr-4"),bH=$APP.S("img.mr-2.w-4"),aH=$APP.S("img.w-4.mr-2"),uH=$APP.S("div.h-full.w-full"),gH=$APP.S("div.text-xl"),cH=$APP.S("div.flexr"),VG=$APP.T("fgl.app.views.dashboard","data"),dH=$APP.S("img.h-30.mr-4"),iH=$APP.S("div.flex.flex-row.justify-around.items-cednter.bg-C81c6dd33.py-3.rounded-lg.fb"),
YG=$APP.S("div.text-3xl.fi.mb-4"),sH=$APP.S("div.px-6"),XG=$APP.S("div.mr-8"),TG=$APP.S("div.rounded-full.bg-C25376f.w-40.h-40.mr-8.self-start"),WG=$APP.S("div.flexrs.mb-4"),nH=$APP.S("div.grow"),$G=$APP.S("span.flexr"),qH=$APP.S("div.grid.justify-items-center.pb-1.border-b.border-C79c5da.pt-1_125rem.fp.font-bold.items-center");$APP.hd("dashboard");var kH=function kH(a){switch(arguments.length){case 1:return kH.o(arguments[0]);case 2:return kH.j(arguments[0],arguments[1]);default:throw Error(["Invalid arity: ",$APP.p.o(arguments.length)].join(""));}};kH.o=function(a){return kH.j(a,$APP.M)};
kH.j=function(a,b){a=$APP.ef?a:[$APP.p.o(a),"rem"].join("");return new $APP.P(null,2,5,$APP.Q,[$APP.Cy,$APP.yn($APP.D([b,new $APP.k(null,2,[$APP.zt,new $APP.k(null,3,[$APP.Et,$APP.wt(a,.81818182),$APP.Bt,a,$APP.Dt,"translateY(5%)"],null),$APP.UD,"/images/landeed-token.png"],null)]))],null)};kH.H=2;$APP.wH={};$APP.Qr(VG,$APP.D([function(a){var b=$APP.N(a);b=$APP.H.j(b,$APP.pA);a=$APP.H.j(a,b);var c=$APP.N(a);a=$APP.H.j(c,$APP.ay);var d=$APP.H.j(c,$APP.nD),e=$APP.H.j(c,$APP.Yv),f=$APP.H.j(c,$APP.mz);c=$APP.H.j(c,$APP.OD);return new $APP.k(null,6,[$APP.ky,b,$APP.sw,a,$APP.Ix,d,$APP.tA,e,$APP.Qy,f,$APP.ny,$APP.Nu(c)],null)}]));$APP.m(localStorage.getItem("user-avatar"))||localStorage.setItem("user-avatar","knight");$APP.Re();
}).call(this);