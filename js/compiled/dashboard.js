(function(){
'use strict';var LJ,OJ,QJ,eK,hK,kK,lK,nK,pK,iK,bK,XJ,PJ,cK,$J,gK,ZJ,TJ,qK,RJ,aK,fK,WJ,oK,VJ,NJ,UJ,dK,jK,YJ,mK;
LJ=function(a){var b=document.createElement("textarea"),c=document.activeElement,d=function(){var f=window.pageYOffset;return $APP.h(f)?f:document.documentElement.scrollTop}();b.style={position:"absolute",left:"-9999px",top:[$APP.m.o(d),"px"].join(""),fontSize:"12pt",border:"0",padding:"0",margin:"0"};b.value=a;b.addEventListener("focus",function(){return window.scrollTo(0,d)});document.body.appendChild(b);b.setSelectionRange(0,b.value.length);b.focus();document.execCommand("copy");b.blur();$APP.h(c)&&
c.focus();window.getSelection().removeAllRanges();window.document.body.removeChild(b)};$APP.MJ=function(){return new $APP.H(null,1,5,$APP.I,[new $APP.g(null,2,[$APP.pp,function(){$APP.P(new $APP.H(null,1,5,$APP.I,[$APP.MD],null));$APP.P(new $APP.H(null,1,5,$APP.I,[$APP.Vz],null));$APP.P(new $APP.H(null,1,5,$APP.I,[$APP.qB],null));$APP.P(new $APP.H(null,1,5,$APP.I,[$APP.Aw],null));return $APP.P(new $APP.H(null,1,5,$APP.I,[$APP.Py],null))},$APP.iB,$APP.Zh],null)],null)};
OJ=function(){var a=$APP.Il.o,b=localStorage.getItem("user-avatar");a=a.call($APP.Il,$APP.h(b)?b:"knight");a:switch(a instanceof $APP.oi?a.ka:null){case "knight":a="/images/avatar-knight.png";break a;default:a="/images/avatar-lord.png"}return new $APP.H(null,2,5,$APP.I,[NJ,new $APP.H(null,2,5,$APP.I,[$APP.xr,new $APP.g(null,1,[$APP.R,a],null)],null)],null)};
QJ=function(){var a=$APP.p($APP.dr(new $APP.H(null,1,5,$APP.I,[PJ],null)));return new $APP.H(null,3,5,$APP.I,[$APP.aH,new $APP.g(null,2,[$APP.$r,"1.5rem",$APP.TC,new $APP.H(null,2,5,$APP.I,[$APP.my,"Do not remind me next time"],null)],null),new $APP.g(null,3,[$APP.Po,"flex flex-row cs1 ce2 rs1 re2 justify-self-start items-center",$APP.yD,function(b){return $APP.P(new $APP.H(null,2,5,$APP.I,[PJ,b],null))},$APP.xD,a],null)],null)};
eK=function(){function a(){return $APP.h(localStorage.getItem("leave-checked"))?b():$APP.P(new $APP.H(null,9,5,$APP.I,[$APP.os,$APP.ys,!0,$APP.Gs,"",$APP.As,new $APP.H(null,4,5,$APP.I,[$APP.BB,new $APP.H(null,2,5,$APP.I,[$APP.aC,"Notice: Since you are trying to leave your current Kingdom, 10% of your Glony will be charged as handling fee. And 24h cool-down will be applied. Click Confirm to proceed."],null),new $APP.H(null,1,5,$APP.I,[$APP.vs],null),new $APP.H(null,1,5,$APP.I,[QJ],null)],null),$APP.Bs,
new $APP.H(null,3,5,$APP.I,[$APP.BB,new $APP.H(null,3,5,$APP.I,[$APP.js,new $APP.g(null,3,[$APP.es,$APP.kF,$APP.ns,$APP.xu.j(b,c),$APP.Po,"mr-8"],null),"Confirm"],null),new $APP.H(null,3,5,$APP.I,[$APP.js,new $APP.g(null,2,[$APP.es,$APP.Cs,$APP.ns,function(){return $APP.P(new $APP.H(null,3,5,$APP.I,[$APP.os,$APP.ps,!0],null))}],null),"Cancel"],null)],null)],null))}function b(){return $APP.P(new $APP.H(null,2,5,$APP.I,[$APP.OE,new $APP.g(null,3,[$APP.pu,$APP.jz,$APP.Gs,"Leave Kingdom",$APP.su,function(){$APP.Ds();
return $APP.P(new $APP.H(null,2,5,$APP.I,[$APP.xE,!0],null))}],null)],null))}function c(){return $APP.h(n)?localStorage.setItem("leave-checked",1):localStorage.removeItem("leave-checked")}var d=$APP.p($APP.dr(new $APP.H(null,1,5,$APP.I,[RJ],null)));d=$APP.E(d);var f=$APP.C.j(d,$APP.Ay),k=$APP.C.j(d,$APP.nC);d=$APP.C.j(d,TJ);var l=$APP.h(d)?d:$APP.aF,n=$APP.p($APP.dr(new $APP.H(null,1,5,$APP.I,[PJ],null)));return new $APP.H(null,3,5,$APP.I,[UJ,new $APP.H(null,3,5,$APP.I,[VJ,new $APP.H(null,2,5,$APP.I,
[WJ,$APP.h(f)?$APP.Er(f):f],null),new $APP.H(null,3,5,$APP.I,[XJ,new $APP.H(null,3,5,$APP.I,[$APP.js,new $APP.g(null,3,[$APP.ns,function(){return open($APP.Dr($APP.y(["/address/",f])))},$APP.es,$APP.gF,$APP.Po,"mr-4"],null),new $APP.H(null,3,5,$APP.I,[YJ,new $APP.H(null,2,5,$APP.I,[ZJ,new $APP.g(null,1,[$APP.R,"/images/share.png"],null)],null),"View on explorer"],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.js,new $APP.g(null,2,[$APP.es,$APP.gF,$APP.ns,function(){LJ(f);return $APP.P(new $APP.H(null,
2,5,$APP.I,[$APP.jC,new $APP.g(null,2,[$APP.Gs,"Copied!",$APP.zE,!0],null)],null))}],null),new $APP.H(null,3,5,$APP.I,[YJ,new $APP.H(null,2,5,$APP.I,[$J,new $APP.g(null,1,[$APP.R,"/images/copy.png"],null)],null),"Copy Address"],null)],null)],null)],null),function(){if($APP.h(k)){var r=0<k;return r?new $APP.H(null,3,5,$APP.I,[$APP.IB,new $APP.H(null,2,5,$APP.I,[aK,new $APP.g(null,1,[$APP.R,["/images/k",$APP.m.o(k),".png"].join("")],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.Vw,new $APP.H(null,3,
5,$APP.I,[bK,new $APP.H(null,2,5,$APP.I,[cK,$APP.C.j($APP.WG,k)],null),new $APP.H(null,2,5,$APP.I,[dK,["ROLE:",$APP.pl(l)].join("")],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.js,new $APP.g(null,2,[$APP.ns,a,$APP.es,$APP.gF],null),"Leave My Kingdom"],null)],null)],null):r}return k}()],null)};
hK=function(){var a=$APP.p($APP.dr(new $APP.H(null,1,5,$APP.I,[RJ],null))),b=$APP.E(a);a=$APP.C.j(b,$APP.Ux);var c=$APP.C.j(b,$APP.Ew),d=$APP.C.j(b,$APP.LA);b=$APP.C.j(b,$APP.lz);return new $APP.H(null,5,5,$APP.I,[fK,new $APP.H(null,3,5,$APP.I,[$APP.sG,new $APP.g(null,1,[$APP.Po,"w-32"],null),new $APP.H(null,3,5,$APP.I,[gK,new $APP.H(null,3,5,$APP.I,[$APP.cH,"3rem",new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.Ar,"-1.4rem 0 -1.4rem -1.4rem"],null)],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.rG,
c,new $APP.g(null,1,[$APP.Po,"mr-2"],null)],null)],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.sG,new $APP.g(null,1,[$APP.Po,"w-32"],null),new $APP.H(null,3,5,$APP.I,[gK,new $APP.H(null,3,5,$APP.I,[$APP.bH,"3rem",new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.Ar,"-1.4rem 0 -1.4rem -1.4rem"],null)],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.rG,a,new $APP.g(null,1,[$APP.Po,"mr-2"],null)],null)],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.sG,new $APP.g(null,1,[$APP.Po,"w-32"],null),new $APP.H(null,
3,5,$APP.I,[gK,new $APP.H(null,3,5,$APP.I,[$APP.tG,"3rem",new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.Ar,"-1.4rem 0 -1.4rem -1.4rem"],null)],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.rG,d,new $APP.g(null,1,[$APP.Po,"mr-2"],null)],null)],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.sG,new $APP.g(null,1,[$APP.Po,"w-32"],null),new $APP.H(null,3,5,$APP.I,[gK,new $APP.H(null,3,5,$APP.I,[$APP.dH,"3rem",new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.Ar,"-1.4rem 0 -1.4rem -1.4rem"],null)],null)],
null),new $APP.H(null,3,5,$APP.I,[$APP.rG,b,new $APP.g(null,1,[$APP.Po,"mr-2"],null)],null)],null)],null)],null)};kK=function(){return new $APP.H(null,3,5,$APP.I,[iK,new $APP.H(null,1,5,$APP.I,[OJ],null),new $APP.H(null,3,5,$APP.I,[jK,new $APP.H(null,1,5,$APP.I,[eK],null),new $APP.H(null,1,5,$APP.I,[hK],null)],null)],null)};lK=function(a){return 10>a?["0",$APP.m.o(a)].join(""):a};
nK=function(a){var b=$APP.A(a,0,null);a=$APP.A(a,1,null);var c=$APP.E(a);a=$APP.C.j(c,$APP.hv);var d=$APP.C.j(c,$APP.iv);c=$APP.I;var f=new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.Ix,"1fr 3fr 1fr"],null)],null),k=$APP.I;d=[$APP.m.o(d.getFullYear()),".",$APP.m.o(lK(d.getMonth()+1)),".",$APP.m.o(lK(d.getDate()))].join("");return $APP.uh(new $APP.H(null,5,5,c,[mK,f,new $APP.H(null,2,5,k,[$APP.us,d],null),new $APP.H(null,3,5,$APP.I,[YJ,new $APP.H(null,2,5,$APP.I,[$APP.cH,"1.5rem"],null),new $APP.H(null,
2,5,$APP.I,[$APP.us,"Glory"],null)],null),new $APP.H(null,2,5,$APP.I,[$APP.rG,a],null)],null),new $APP.g(null,1,[$APP.Vm,b],null))};
pK=function(){return new $APP.H(null,3,5,$APP.I,[oK,new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.Yr,"calc(100% - 180px - 7.5rem)"],null)],null),new $APP.H(null,4,5,$APP.I,[$APP.zw,new $APP.g(null,1,[$APP.Q,new $APP.g(null,3,[$APP.Yr,"calc(100% - 2.25rem)",$APP.lA,"2px ridge #50929e33",$APP.wr,"linear-gradient(\n    rgba(163, 218, 226, 0.1),\n    rgba(163, 218, 226, 0.05)\n  )"],null)],null),new $APP.H(null,5,5,$APP.I,[$APP.eE,new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.Ix,"1fr 3fr 1fr"],
null)],null),new $APP.H(null,2,5,$APP.I,[$APP.us,"DATE"],null),new $APP.H(null,2,5,$APP.I,[$APP.us,"ASSET"],null),new $APP.H(null,2,5,$APP.I,[$APP.us,"sGlory locked"],null)],null),function(){var a=$APP.p($APP.dr(new $APP.H(null,1,5,$APP.I,[RJ],null)));a=$APP.E(a);a=$APP.C.j(a,$APP.Dy);return new $APP.H(null,3,5,$APP.I,[$APP.cj,new $APP.H(null,2,5,$APP.I,[$APP.VE,new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.Yr,"calc(100% - 1.25rem)"],null)],null)],null),$APP.fj(function(b,c){return new $APP.H(null,
2,5,$APP.I,[nK,new $APP.H(null,2,5,$APP.I,[b,c],null)],null)},a)],null)}()],null)],null)};$APP.rK=function(){return new $APP.H(null,3,5,$APP.I,[qK,new $APP.H(null,1,5,$APP.I,[kK],null),new $APP.H(null,1,5,$APP.I,[pK],null)],null)};iK=$APP.K("div.flex.flex-row.justify-between.items-center.p-8");bK=$APP.K("div.flex.flex-row.items-baseline.mb-4");XJ=$APP.K("div.flex.flex-row.justify-between.items-center");PJ=$APP.M("fgl.app.views.dashboard","leave-checked");cK=$APP.K("div.uppercase.text-3xl.fi.mr-4");
$J=$APP.K("img.mr-2.w-4");gK=$APP.K("div.flex.flex-row.justify-between.items-center.text-xl");ZJ=$APP.K("img.w-4.mr-2");TJ=$APP.K("kingdom-role");qK=$APP.K("div.h-full.w-full");RJ=$APP.M("fgl.app.views.dashboard","data");aK=$APP.K("img.h-30.mr-4");fK=$APP.K("div.flex.flex-row.justify-around.items-cednter.bg-C81c6dd33.py-3.rounded-lg.fb");WJ=$APP.K("div.text-3xl.fi.mb-4");oK=$APP.K("div.px-6");VJ=$APP.K("div.mr-8");NJ=$APP.K("div.rounded-full.bg-C25376f.w-40.h-40.mr-8.self-start");UJ=$APP.K("div.flexrs.mb-4");
dK=$APP.K("div.text-xl.uppercase");jK=$APP.K("div.grow");YJ=$APP.K("span.flexr");mK=$APP.K("div.grid.justify-items-center.pb-1.border-b.border-C79c5da.pt-1_125rem.fp.font-bold.items-center");$APP.cd("dashboard");$APP.sK={};$APP.br(RJ,$APP.y([function(a){var b=$APP.E(a);b=$APP.C.j(b,$APP.Ju);a=$APP.C.j(a,b);var c=$APP.E(a);a=$APP.C.j(c,$APP.ry);var d=$APP.C.j(c,$APP.ZD),f=$APP.C.j(c,$APP.iw),k=$APP.C.j(c,$APP.Kz),l=$APP.C.j(c,$APP.BE),n=$APP.C.j(c,$APP.Ru);c=$APP.C.j(c,$APP.Uu);return new $APP.g(null,8,[$APP.Ay,b,$APP.Ew,a,$APP.Ux,d,$APP.LA,f,$APP.lz,k,$APP.nC,n,TJ,c,$APP.Dy,$APP.jv(l)],null)}]));$APP.h(localStorage.getItem("user-avatar"))||localStorage.setItem("user-avatar","knight");
$APP.xF.j(PJ,function(a,b){$APP.A(b,0,null);b=$APP.A(b,1,null);return $APP.J.A(a,PJ,b)});$APP.br(PJ,$APP.y([function(a){return $APP.C.A(a,PJ,!1)}]));$APP.Je();
}).call(this);