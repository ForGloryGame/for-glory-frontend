(function(){
'use strict';var KJ,NJ,PJ,dK,gK,jK,kK,mK,oK,hK,aK,WJ,OJ,bK,ZJ,fK,YJ,SJ,pK,QJ,$J,eK,VJ,nK,UJ,MJ,TJ,cK,iK,XJ,lK;
KJ=function(a){var b=document.createElement("textarea"),c=document.activeElement,d=function(){var e=window.pageYOffset;return $APP.h(e)?e:document.documentElement.scrollTop}();b.style={position:"absolute",left:"-9999px",top:[$APP.m.o(d),"px"].join(""),fontSize:"12pt",border:"0",padding:"0",margin:"0"};b.value=a;b.addEventListener("focus",function(){return window.scrollTo(0,d)});document.body.appendChild(b);b.setSelectionRange(0,b.value.length);b.focus();document.execCommand("copy");b.blur();$APP.h(c)&&
c.focus();window.getSelection().removeAllRanges();window.document.body.removeChild(b)};$APP.LJ=function(){return new $APP.H(null,1,5,$APP.I,[new $APP.g(null,2,[$APP.Cp,function(){$APP.P(new $APP.H(null,1,5,$APP.I,[$APP.KD],null));$APP.P(new $APP.H(null,1,5,$APP.I,[$APP.Tz],null));$APP.P(new $APP.H(null,1,5,$APP.I,[$APP.nB],null));$APP.P(new $APP.H(null,1,5,$APP.I,[$APP.Aw],null));return $APP.P(new $APP.H(null,1,5,$APP.I,[$APP.Py],null))},$APP.fB,$APP.Zh],null)],null)};
NJ=function(){var a=$APP.Il.o,b=localStorage.getItem("user-avatar");a=a.call($APP.Il,$APP.h(b)?b:"knight");a:switch(a instanceof $APP.oi?a.ka:null){case "knight":a="/images/avatar-knight.png";break a;default:a="/images/avatar-lord.png"}return new $APP.H(null,2,5,$APP.I,[MJ,new $APP.H(null,2,5,$APP.I,[$APP.ur,new $APP.g(null,1,[$APP.R,a],null)],null)],null)};
PJ=function(){var a=$APP.p($APP.qr(new $APP.H(null,1,5,$APP.I,[OJ],null)));return new $APP.H(null,3,5,$APP.I,[$APP.$G,new $APP.g(null,2,[$APP.Xr,"1.5rem",$APP.RC,new $APP.H(null,2,5,$APP.I,[$APP.my,"Do not remind me next time"],null)],null),new $APP.g(null,3,[$APP.bp,"flex flex-row cs1 ce2 rs1 re2 justify-self-start items-center",$APP.wD,function(b){return $APP.P(new $APP.H(null,2,5,$APP.I,[OJ,b],null))},$APP.vD,a],null)],null)};
dK=function(){function a(){return $APP.h(localStorage.getItem("leave-checked"))?b():$APP.P(new $APP.H(null,9,5,$APP.I,[$APP.ls,$APP.vs,!0,$APP.Ds,"",$APP.xs,new $APP.H(null,4,5,$APP.I,[$APP.yB,new $APP.H(null,2,5,$APP.I,[$APP.ZB,"Notice: Since you are trying to leave your current Kingdom, 10% of your Glony will be charged as handling fee. And 24h cool-down will be applied. Click Confirm to proceed."],null),new $APP.H(null,1,5,$APP.I,[$APP.ss],null),new $APP.H(null,1,5,$APP.I,[PJ],null)],null),$APP.ys,
new $APP.H(null,3,5,$APP.I,[$APP.yB,new $APP.H(null,3,5,$APP.I,[$APP.gs,new $APP.g(null,3,[$APP.bs,$APP.jF,$APP.ks,$APP.uu.j(b,c),$APP.bp,"mr-8"],null),"Confirm"],null),new $APP.H(null,3,5,$APP.I,[$APP.gs,new $APP.g(null,2,[$APP.bs,$APP.zs,$APP.ks,function(){return $APP.P(new $APP.H(null,3,5,$APP.I,[$APP.ls,$APP.ms,!0],null))}],null),"Cancel"],null)],null)],null))}function b(){return $APP.P(new $APP.H(null,2,5,$APP.I,[$APP.ME,new $APP.g(null,3,[$APP.mu,$APP.jz,$APP.Ds,"Leave Kingdom",$APP.pu,function(){$APP.As();
return $APP.P(new $APP.H(null,2,5,$APP.I,[$APP.vE,!0],null))}],null)],null))}function c(){return $APP.h(n)?localStorage.setItem("leave-checked",1):localStorage.removeItem("leave-checked")}var d=$APP.p($APP.qr(new $APP.H(null,1,5,$APP.I,[QJ],null)));d=$APP.E(d);var e=$APP.C.j(d,$APP.Ay),k=$APP.C.j(d,$APP.lC);d=$APP.C.j(d,SJ);var l=$APP.h(d)?d:$APP.$E,n=$APP.p($APP.qr(new $APP.H(null,1,5,$APP.I,[OJ],null)));return new $APP.H(null,3,5,$APP.I,[TJ,new $APP.H(null,3,5,$APP.I,[UJ,new $APP.H(null,2,5,$APP.I,
[VJ,$APP.h(e)?$APP.Br(e):e],null),new $APP.H(null,3,5,$APP.I,[WJ,new $APP.H(null,3,5,$APP.I,[$APP.gs,new $APP.g(null,3,[$APP.ks,function(){return open($APP.Ar($APP.y(["/address/",e])))},$APP.bs,$APP.fF,$APP.bp,"mr-4"],null),new $APP.H(null,3,5,$APP.I,[XJ,new $APP.H(null,2,5,$APP.I,[YJ,new $APP.g(null,1,[$APP.R,"/images/share.png"],null)],null),"View on explorer"],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.gs,new $APP.g(null,2,[$APP.bs,$APP.fF,$APP.ks,function(){KJ(e);return $APP.P(new $APP.H(null,
2,5,$APP.I,[$APP.hC,new $APP.g(null,2,[$APP.Ds,"Copied!",$APP.xE,!0],null)],null))}],null),new $APP.H(null,3,5,$APP.I,[XJ,new $APP.H(null,2,5,$APP.I,[ZJ,new $APP.g(null,1,[$APP.R,"/images/copy.png"],null)],null),"Copy Address"],null)],null)],null)],null),function(){if($APP.h(k)){var r=0<k;return r?new $APP.H(null,3,5,$APP.I,[$APP.FB,new $APP.H(null,2,5,$APP.I,[$J,new $APP.g(null,1,[$APP.R,["/images/k",$APP.m.o(k),".png"].join("")],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.Vw,new $APP.H(null,3,
5,$APP.I,[aK,new $APP.H(null,2,5,$APP.I,[bK,$APP.C.j($APP.VG,k)],null),new $APP.H(null,2,5,$APP.I,[cK,["ROLE:",$APP.pl(l)].join("")],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.gs,new $APP.g(null,2,[$APP.ks,a,$APP.bs,$APP.fF],null),"Leave My Kingdom"],null)],null)],null):r}return k}()],null)};
gK=function(){var a=$APP.p($APP.qr(new $APP.H(null,1,5,$APP.I,[QJ],null))),b=$APP.E(a);a=$APP.C.j(b,$APP.Ux);var c=$APP.C.j(b,$APP.Ew),d=$APP.C.j(b,$APP.IA);b=$APP.C.j(b,$APP.lz);return new $APP.H(null,5,5,$APP.I,[eK,new $APP.H(null,3,5,$APP.I,[$APP.rG,new $APP.g(null,1,[$APP.bp,"w-32"],null),new $APP.H(null,3,5,$APP.I,[fK,new $APP.H(null,3,5,$APP.I,[$APP.bH,"3rem",new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.xr,"-1.4rem 0 -1.4rem -1.4rem"],null)],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.qG,
c,new $APP.g(null,1,[$APP.bp,"mr-2"],null)],null)],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.rG,new $APP.g(null,1,[$APP.bp,"w-32"],null),new $APP.H(null,3,5,$APP.I,[fK,new $APP.H(null,3,5,$APP.I,[$APP.aH,"3rem",new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.xr,"-1.4rem 0 -1.4rem -1.4rem"],null)],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.qG,a,new $APP.g(null,1,[$APP.bp,"mr-2"],null)],null)],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.rG,new $APP.g(null,1,[$APP.bp,"w-32"],null),new $APP.H(null,
3,5,$APP.I,[fK,new $APP.H(null,3,5,$APP.I,[$APP.sG,"3rem",new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.xr,"-1.4rem 0 -1.4rem -1.4rem"],null)],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.qG,d,new $APP.g(null,1,[$APP.bp,"mr-2"],null)],null)],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.rG,new $APP.g(null,1,[$APP.bp,"w-32"],null),new $APP.H(null,3,5,$APP.I,[fK,new $APP.H(null,3,5,$APP.I,[$APP.cH,"3rem",new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.xr,"-1.4rem 0 -1.4rem -1.4rem"],null)],null)],
null),new $APP.H(null,3,5,$APP.I,[$APP.qG,b,new $APP.g(null,1,[$APP.bp,"mr-2"],null)],null)],null)],null)],null)};jK=function(){return new $APP.H(null,3,5,$APP.I,[hK,new $APP.H(null,1,5,$APP.I,[NJ],null),new $APP.H(null,3,5,$APP.I,[iK,new $APP.H(null,1,5,$APP.I,[dK],null),new $APP.H(null,1,5,$APP.I,[gK],null)],null)],null)};kK=function(a){return 10>a?["0",$APP.m.o(a)].join(""):a};
mK=function(a){var b=$APP.A(a,0,null);a=$APP.A(a,1,null);var c=$APP.E(a);a=$APP.C.j(c,$APP.ev);var d=$APP.C.j(c,$APP.fv);c=$APP.I;var e=new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.Ix,"1fr 3fr 1fr"],null)],null),k=$APP.I;d=[$APP.m.o(d.getFullYear()),".",$APP.m.o(kK(d.getMonth()+1)),".",$APP.m.o(kK(d.getDate()))].join("");return $APP.uh(new $APP.H(null,5,5,c,[lK,e,new $APP.H(null,2,5,k,[$APP.rs,d],null),new $APP.H(null,3,5,$APP.I,[XJ,new $APP.H(null,2,5,$APP.I,[$APP.bH,"1.5rem"],null),new $APP.H(null,
2,5,$APP.I,[$APP.rs,"Glory"],null)],null),new $APP.H(null,2,5,$APP.I,[$APP.qG,a],null)],null),new $APP.g(null,1,[$APP.hn,b],null))};
oK=function(){return new $APP.H(null,3,5,$APP.I,[nK,new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.Vr,"calc(100% - 180px - 7.5rem)"],null)],null),new $APP.H(null,4,5,$APP.I,[$APP.zw,new $APP.g(null,1,[$APP.Q,new $APP.g(null,3,[$APP.Vr,"calc(100% - 2.25rem)",$APP.jA,"2px ridge #50929e33",$APP.tr,"linear-gradient(\n    rgba(163, 218, 226, 0.1),\n    rgba(163, 218, 226, 0.05)\n  )"],null)],null),new $APP.H(null,5,5,$APP.I,[$APP.cE,new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.Ix,"1fr 3fr 1fr"],
null)],null),new $APP.H(null,2,5,$APP.I,[$APP.rs,"DATE"],null),new $APP.H(null,2,5,$APP.I,[$APP.rs,"ASSET"],null),new $APP.H(null,2,5,$APP.I,[$APP.rs,"sGlory locked"],null)],null),function(){var a=$APP.p($APP.qr(new $APP.H(null,1,5,$APP.I,[QJ],null)));a=$APP.E(a);a=$APP.C.j(a,$APP.Dy);return new $APP.H(null,3,5,$APP.I,[$APP.cj,new $APP.H(null,2,5,$APP.I,[$APP.UE,new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.Vr,"calc(100% - 1.25rem)"],null)],null)],null),$APP.fj(function(b,c){return new $APP.H(null,
2,5,$APP.I,[mK,new $APP.H(null,2,5,$APP.I,[b,c],null)],null)},a)],null)}()],null)],null)};$APP.qK=function(){return new $APP.H(null,3,5,$APP.I,[pK,new $APP.H(null,1,5,$APP.I,[jK],null),new $APP.H(null,1,5,$APP.I,[oK],null)],null)};hK=$APP.K("div.flex.flex-row.justify-between.items-center.p-8");aK=$APP.K("div.flex.flex-row.items-baseline.mb-4");WJ=$APP.K("div.flex.flex-row.justify-between.items-center");OJ=$APP.M("fgl.app.views.dashboard","leave-checked");bK=$APP.K("div.uppercase.text-3xl.fi.mr-4");
ZJ=$APP.K("img.mr-2.w-4");fK=$APP.K("div.flex.flex-row.justify-between.items-center.text-xl");YJ=$APP.K("img.w-4.mr-2");SJ=$APP.K("kingdom-role");pK=$APP.K("div.h-full.w-full");QJ=$APP.M("fgl.app.views.dashboard","data");$J=$APP.K("img.h-30.mr-4");eK=$APP.K("div.flex.flex-row.justify-around.items-cednter.bg-C81c6dd33.py-3.rounded-lg.fb");VJ=$APP.K("div.text-3xl.fi.mb-4");nK=$APP.K("div.px-6");UJ=$APP.K("div.mr-8");MJ=$APP.K("div.rounded-full.bg-C25376f.w-40.h-40.mr-8.self-start");TJ=$APP.K("div.flexrs.mb-4");
cK=$APP.K("div.text-xl.uppercase");iK=$APP.K("div.grow");XJ=$APP.K("span.flexr");lK=$APP.K("div.grid.justify-items-center.pb-1.border-b.border-C79c5da.pt-1_125rem.fp.font-bold.items-center");$APP.cd("dashboard");$APP.rK={};$APP.or(QJ,$APP.y([function(a){var b=$APP.E(a);b=$APP.C.j(b,$APP.Gu);a=$APP.C.j(a,b);var c=$APP.E(a);a=$APP.C.j(c,$APP.ry);var d=$APP.C.j(c,$APP.WD),e=$APP.C.j(c,$APP.iw),k=$APP.C.j(c,$APP.Jz),l=$APP.C.j(c,$APP.zE),n=$APP.C.j(c,$APP.Ou);c=$APP.C.j(c,$APP.Ru);return new $APP.g(null,8,[$APP.Ay,b,$APP.Ew,a,$APP.Ux,d,$APP.IA,e,$APP.lz,k,$APP.lC,n,SJ,c,$APP.Dy,$APP.gv(l)],null)}]));$APP.h(localStorage.getItem("user-avatar"))||localStorage.setItem("user-avatar","knight");
$APP.bG.j(OJ,function(a,b){$APP.A(b,0,null);b=$APP.A(b,1,null);return $APP.J.A(a,OJ,b)});$APP.or(OJ,$APP.y([function(a){return $APP.C.A(a,OJ,!1)}]));$APP.Je();
}).call(this);