(function(){
'use strict';var BJ,EJ,HJ,VJ,YJ,aK,bK,dK,fK,ZJ,SJ,NJ,GJ,TJ,QJ,XJ,PJ,JJ,gK,IJ,RJ,WJ,MJ,eK,LJ,DJ,KJ,UJ,$J,OJ,cK;
BJ=function(a){var b=document.createElement("textarea"),c=document.activeElement,d=function(){var e=window.pageYOffset;return $APP.h(e)?e:document.documentElement.scrollTop}();b.style={position:"absolute",left:"-9999px",top:[$APP.m.o(d),"px"].join(""),fontSize:"12pt",border:"0",padding:"0",margin:"0"};b.value=a;b.addEventListener("focus",function(){return window.scrollTo(0,d)});document.body.appendChild(b);b.setSelectionRange(0,b.value.length);b.focus();document.execCommand("copy");b.blur();$APP.h(c)&&
c.focus();window.getSelection().removeAllRanges();window.document.body.removeChild(b)};$APP.CJ=function(){return new $APP.H(null,1,5,$APP.I,[new $APP.g(null,2,[$APP.Cp,function(){$APP.P(new $APP.H(null,1,5,$APP.I,[$APP.CD],null));$APP.P(new $APP.H(null,1,5,$APP.I,[$APP.Qz],null));$APP.P(new $APP.H(null,1,5,$APP.I,[$APP.jB],null));$APP.P(new $APP.H(null,1,5,$APP.I,[$APP.xw],null));return $APP.P(new $APP.H(null,1,5,$APP.I,[$APP.My],null))},$APP.bB,$APP.Zh],null)],null)};
EJ=function(){var a=$APP.Il.o,b=localStorage.getItem("user-avatar");a=a.call($APP.Il,$APP.h(b)?b:"knight");a:switch(a instanceof $APP.oi?a.ka:null){case "knight":a="/images/avatar-knight.png";break a;default:a="/images/avatar-lord.png"}return new $APP.H(null,2,5,$APP.I,[DJ,new $APP.H(null,2,5,$APP.I,[$APP.ur,new $APP.g(null,1,[$APP.R,a],null)],null)],null)};
HJ=function(){var a=$APP.p($APP.qr(new $APP.H(null,1,5,$APP.I,[GJ],null)));return new $APP.H(null,3,5,$APP.I,[$APP.RG,new $APP.g(null,2,[$APP.Xr,"1.5rem",$APP.LC,new $APP.H(null,2,5,$APP.I,[$APP.jy,"Do not remind me next time"],null)],null),new $APP.g(null,3,[$APP.bp,"flex flex-row cs1 ce2 rs1 re2 justify-self-start items-center",$APP.pD,function(b){return $APP.P(new $APP.H(null,2,5,$APP.I,[GJ,b],null))},$APP.oD,a],null)],null)};
VJ=function(){function a(){return $APP.h(localStorage.getItem("leave-checked"))?b():$APP.P(new $APP.H(null,9,5,$APP.I,[$APP.ls,$APP.vs,!0,$APP.Ds,"",$APP.xs,new $APP.H(null,4,5,$APP.I,[$APP.vB,new $APP.H(null,2,5,$APP.I,[$APP.VB,"Notice: Since you are trying to leave your current Kingdom, 10% of your Glony will be charged as handling fee. And 24h cool-down will be applied. Click Confirm to proceed."],null),new $APP.H(null,1,5,$APP.I,[$APP.ss],null),new $APP.H(null,1,5,$APP.I,[HJ],null)],null),$APP.ys,
new $APP.H(null,3,5,$APP.I,[$APP.vB,new $APP.H(null,3,5,$APP.I,[$APP.gs,new $APP.g(null,3,[$APP.bs,$APP.aF,$APP.ks,$APP.uu.j(b,c),$APP.bp,"mr-8"],null),"Confirm"],null),new $APP.H(null,3,5,$APP.I,[$APP.gs,new $APP.g(null,2,[$APP.bs,$APP.zs,$APP.ks,function(){return $APP.P(new $APP.H(null,3,5,$APP.I,[$APP.ls,$APP.ms,!0],null))}],null),"Cancel"],null)],null)],null))}function b(){return $APP.P(new $APP.H(null,2,5,$APP.I,[$APP.DE,new $APP.g(null,3,[$APP.mu,$APP.gz,$APP.Ds,"Leave Kingdom",$APP.pu,function(){$APP.As();
return $APP.P(new $APP.H(null,2,5,$APP.I,[$APP.mE,!0],null))}],null)],null))}function c(){return $APP.h(n)?localStorage.setItem("leave-checked",1):localStorage.removeItem("leave-checked")}var d=$APP.p($APP.qr(new $APP.H(null,1,5,$APP.I,[IJ],null)));d=$APP.E(d);var e=$APP.C.j(d,$APP.xy),k=$APP.C.j(d,$APP.gC);d=$APP.C.j(d,JJ);var l=$APP.h(d)?d:$APP.RE,n=$APP.p($APP.qr(new $APP.H(null,1,5,$APP.I,[GJ],null)));return new $APP.H(null,3,5,$APP.I,[KJ,new $APP.H(null,3,5,$APP.I,[LJ,new $APP.H(null,2,5,$APP.I,
[MJ,$APP.h(e)?$APP.Br(e):e],null),new $APP.H(null,3,5,$APP.I,[NJ,new $APP.H(null,3,5,$APP.I,[$APP.gs,new $APP.g(null,3,[$APP.ks,function(){return open($APP.Ar($APP.y(["/address/",e])))},$APP.bs,$APP.XE,$APP.bp,"mr-4"],null),new $APP.H(null,3,5,$APP.I,[OJ,new $APP.H(null,2,5,$APP.I,[PJ,new $APP.g(null,1,[$APP.R,"/images/share.png"],null)],null),"View on explorer"],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.gs,new $APP.g(null,2,[$APP.bs,$APP.XE,$APP.ks,function(){BJ(e);return $APP.P(new $APP.H(null,
2,5,$APP.I,[$APP.cC,new $APP.g(null,2,[$APP.Ds,"Copied!",$APP.oE,!0],null)],null))}],null),new $APP.H(null,3,5,$APP.I,[OJ,new $APP.H(null,2,5,$APP.I,[QJ,new $APP.g(null,1,[$APP.R,"/images/copy.png"],null)],null),"Copy Address"],null)],null)],null)],null),function(){if($APP.h(k)){var r=0<k;return r?new $APP.H(null,3,5,$APP.I,[$APP.CB,new $APP.H(null,2,5,$APP.I,[RJ,new $APP.g(null,1,[$APP.R,["/images/k",$APP.m.o(k),".png"].join("")],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.Sw,new $APP.H(null,3,
5,$APP.I,[SJ,new $APP.H(null,2,5,$APP.I,[TJ,$APP.C.j($APP.MG,k)],null),new $APP.H(null,2,5,$APP.I,[UJ,["ROLE:",$APP.pl(l)].join("")],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.gs,new $APP.g(null,2,[$APP.ks,a,$APP.bs,$APP.XE],null),"Leave My Kingdom"],null)],null)],null):r}return k}()],null)};
YJ=function(){var a=$APP.p($APP.qr(new $APP.H(null,1,5,$APP.I,[IJ],null))),b=$APP.E(a);a=$APP.C.j(b,$APP.Rx);var c=$APP.C.j(b,$APP.Bw),d=$APP.C.j(b,$APP.FA);b=$APP.C.j(b,$APP.iz);return new $APP.H(null,5,5,$APP.I,[WJ,new $APP.H(null,3,5,$APP.I,[$APP.iG,new $APP.g(null,1,[$APP.bp,"w-32"],null),new $APP.H(null,3,5,$APP.I,[XJ,new $APP.H(null,3,5,$APP.I,[$APP.TG,"3rem",new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.xr,"-1.4rem 0 -1.4rem -1.4rem"],null)],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.hG,
c,new $APP.g(null,1,[$APP.bp,"mr-2"],null)],null)],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.iG,new $APP.g(null,1,[$APP.bp,"w-32"],null),new $APP.H(null,3,5,$APP.I,[XJ,new $APP.H(null,3,5,$APP.I,[$APP.SG,"3rem",new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.xr,"-1.4rem 0 -1.4rem -1.4rem"],null)],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.hG,a,new $APP.g(null,1,[$APP.bp,"mr-2"],null)],null)],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.iG,new $APP.g(null,1,[$APP.bp,"w-32"],null),new $APP.H(null,
3,5,$APP.I,[XJ,new $APP.H(null,3,5,$APP.I,[$APP.jG,"3rem",new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.xr,"-1.4rem 0 -1.4rem -1.4rem"],null)],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.hG,d,new $APP.g(null,1,[$APP.bp,"mr-2"],null)],null)],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.iG,new $APP.g(null,1,[$APP.bp,"w-32"],null),new $APP.H(null,3,5,$APP.I,[XJ,new $APP.H(null,3,5,$APP.I,[$APP.UG,"3rem",new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.xr,"-1.4rem 0 -1.4rem -1.4rem"],null)],null)],
null),new $APP.H(null,3,5,$APP.I,[$APP.hG,b,new $APP.g(null,1,[$APP.bp,"mr-2"],null)],null)],null)],null)],null)};aK=function(){return new $APP.H(null,3,5,$APP.I,[ZJ,new $APP.H(null,1,5,$APP.I,[EJ],null),new $APP.H(null,3,5,$APP.I,[$J,new $APP.H(null,1,5,$APP.I,[VJ],null),new $APP.H(null,1,5,$APP.I,[YJ],null)],null)],null)};bK=function(a){return 10>a?["0",$APP.m.o(a)].join(""):a};
dK=function(a){var b=$APP.A(a,0,null);a=$APP.A(a,1,null);var c=$APP.E(a);a=$APP.C.j(c,$APP.ev);var d=$APP.C.j(c,$APP.fv);c=$APP.I;var e=new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.Fx,"1fr 3fr 1fr"],null)],null),k=$APP.I;d=[$APP.m.o(d.getFullYear()),".",$APP.m.o(bK(d.getMonth()+1)),".",$APP.m.o(bK(d.getDate()))].join("");return $APP.uh(new $APP.H(null,5,5,c,[cK,e,new $APP.H(null,2,5,k,[$APP.rs,d],null),new $APP.H(null,3,5,$APP.I,[OJ,new $APP.H(null,2,5,$APP.I,[$APP.TG,"1.5rem"],null),new $APP.H(null,
2,5,$APP.I,[$APP.rs,"Glory"],null)],null),new $APP.H(null,2,5,$APP.I,[$APP.hG,a],null)],null),new $APP.g(null,1,[$APP.hn,b],null))};
fK=function(){return new $APP.H(null,3,5,$APP.I,[eK,new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.Vr,"calc(100% - 180px - 7.5rem)"],null)],null),new $APP.H(null,4,5,$APP.I,[$APP.ww,new $APP.g(null,1,[$APP.Q,new $APP.g(null,3,[$APP.Vr,"calc(100% - 2.25rem)",$APP.gA,"2px ridge #50929e33",$APP.tr,"linear-gradient(\n    rgba(163, 218, 226, 0.1),\n    rgba(163, 218, 226, 0.05)\n  )"],null)],null),new $APP.H(null,5,5,$APP.I,[$APP.VD,new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.Fx,"1fr 3fr 1fr"],
null)],null),new $APP.H(null,2,5,$APP.I,[$APP.rs,"DATE"],null),new $APP.H(null,2,5,$APP.I,[$APP.rs,"ASSET"],null),new $APP.H(null,2,5,$APP.I,[$APP.rs,"sGlory locked"],null)],null),function(){var a=$APP.p($APP.qr(new $APP.H(null,1,5,$APP.I,[IJ],null)));a=$APP.E(a);a=$APP.C.j(a,$APP.Ay);return new $APP.H(null,3,5,$APP.I,[$APP.cj,new $APP.H(null,2,5,$APP.I,[$APP.LE,new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.Vr,"calc(100% - 1.25rem)"],null)],null)],null),$APP.fj(function(b,c){return new $APP.H(null,
2,5,$APP.I,[dK,new $APP.H(null,2,5,$APP.I,[b,c],null)],null)},a)],null)}()],null)],null)};$APP.hK=function(){return new $APP.H(null,3,5,$APP.I,[gK,new $APP.H(null,1,5,$APP.I,[aK],null),new $APP.H(null,1,5,$APP.I,[fK],null)],null)};ZJ=$APP.K("div.flex.flex-row.justify-between.items-center.p-8");SJ=$APP.K("div.flex.flex-row.items-baseline.mb-4");NJ=$APP.K("div.flex.flex-row.justify-between.items-center");GJ=$APP.M("fgl.app.views.dashboard","leave-checked");TJ=$APP.K("div.uppercase.text-3xl.fi.mr-4");
QJ=$APP.K("img.mr-2.w-4");XJ=$APP.K("div.flex.flex-row.justify-between.items-center.text-xl");PJ=$APP.K("img.w-4.mr-2");JJ=$APP.K("kingdom-role");gK=$APP.K("div.h-full.w-full");IJ=$APP.M("fgl.app.views.dashboard","data");RJ=$APP.K("img.h-30.mr-4");WJ=$APP.K("div.flex.flex-row.justify-around.items-cednter.bg-C81c6dd33.py-3.rounded-lg.fb");MJ=$APP.K("div.text-3xl.fi.mb-4");eK=$APP.K("div.px-6");LJ=$APP.K("div.mr-8");DJ=$APP.K("div.rounded-full.bg-C25376f.w-40.h-40.mr-8.self-start");KJ=$APP.K("div.flexrs.mb-4");
UJ=$APP.K("div.text-xl.uppercase");$J=$APP.K("div.grow");OJ=$APP.K("span.flexr");cK=$APP.K("div.grid.justify-items-center.pb-1.border-b.border-C79c5da.pt-1_125rem.fp.font-bold.items-center");$APP.cd("dashboard");$APP.iK={};$APP.or(IJ,$APP.y([function(a){var b=$APP.E(a);b=$APP.C.j(b,$APP.Gu);a=$APP.C.j(a,b);var c=$APP.E(a);a=$APP.C.j(c,$APP.oy);var d=$APP.C.j(c,$APP.OD),e=$APP.C.j(c,$APP.fw),k=$APP.C.j(c,$APP.Gz),l=$APP.C.j(c,$APP.qE),n=$APP.C.j(c,$APP.Ou);c=$APP.C.j(c,$APP.Ru);return new $APP.g(null,8,[$APP.xy,b,$APP.Bw,a,$APP.Rx,d,$APP.FA,e,$APP.iz,k,$APP.gC,n,JJ,c,$APP.Ay,$APP.gv(l)],null)}]));$APP.h(localStorage.getItem("user-avatar"))||localStorage.setItem("user-avatar","knight");
$APP.TF.j(GJ,function(a,b){$APP.A(b,0,null);b=$APP.A(b,1,null);return $APP.J.A(a,GJ,b)});$APP.or(GJ,$APP.y([function(a){return $APP.C.A(a,GJ,!1)}]));$APP.Je();
}).call(this);