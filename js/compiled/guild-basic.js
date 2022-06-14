(function(){
'use strict';var bT,cT,dT,eT,gT,iT,kT,uT,xT;bT=function(a){this.Ra=a;this.F=32769;this.P=0};cT=function(){return new bT($APP.hj(0))};dT=function(a){switch(arguments.length){case 0:return cT();case 1:return new bT($APP.hj(arguments[0]));default:throw Error(["Invalid arity: ",$APP.n.o(arguments.length)].join(""));}};eT=function(a,b,c){var d=cT?cT():dT.call(null);return $APP.cf.A(function(e,k){var l=d.D?d.D():d.call(null);return a.A?a.A(e,l,k):a.call(null,e,l,k)},b,c)};
$APP.rM=function(){return new $APP.I(null,1,5,$APP.J,[new $APP.g(null,2,[$APP.Cq,function(){$APP.Q(new $APP.I(null,1,5,$APP.J,[$APP.qC],null));$APP.Q(new $APP.I(null,2,5,$APP.J,[$APP.JI,!0],null));$APP.Q(new $APP.I(null,1,5,$APP.J,[$APP.VF],null));return $APP.Q(new $APP.I(null,1,5,$APP.J,[$APP.RD],null))},$APP.Av,$APP.Zh],null)],null)};
gT=function(a,b){return $APP.hq(new $APP.g(null,2,[$APP.qE,function(){return $APP.Q(new $APP.I(null,2,5,$APP.J,[$APP.kz,b],null))},$APP.Tp,function(){var c=$APP.p($APP.qs(new $APP.I(null,2,5,$APP.J,[$APP.kz,b],null)));return $APP.uh(new $APP.I(null,5,5,$APP.J,[fT,new $APP.g(null,1,[$APP.On,new $APP.g(null,1,[$APP.aB,"1fr 3fr 1fr"],null)],null),new $APP.I(null,2,5,$APP.J,[$APP.Bs,"Elder"],null),new $APP.I(null,2,5,$APP.J,[$APP.Bs,b],null),new $APP.I(null,2,5,$APP.J,[$APP.wK,c],null)],null),new $APP.g(null,
1,[$APP.oo,a],null))}],null))};iT=function(a){return new $APP.I(null,2,5,$APP.J,[hT,new $APP.g(null,1,[$APP.R,a],null)],null)};
kT=function(){var a=$APP.p($APP.qs(new $APP.I(null,1,5,$APP.J,[jT],null)));return new $APP.I(null,3,5,$APP.J,[$APP.sL,new $APP.g(null,2,[$APP.Tn,"1.5rem",$APP.YG,new $APP.I(null,2,5,$APP.J,[$APP.JB,"Do not remind me next time"],null)],null),new $APP.g(null,3,[$APP.Zn,"flex flex-row cs1 ce2 rs1 re2 justify-self-start items-center",$APP.AH,function(b){return $APP.Q(new $APP.I(null,2,5,$APP.J,[jT,b],null))},$APP.zH,a],null)],null)};
uT=function(){function a(){return $APP.Q(new $APP.I(null,2,5,$APP.J,[$APP.bJ,new $APP.g(null,3,[$APP.wu,$APP.OC,$APP.Ns,"Leave Kingdom",$APP.zu,function(){$APP.Ks();return $APP.Q(new $APP.I(null,2,5,$APP.J,[$APP.JI,!0],null))}],null)],null))}function b(){return $APP.h(l)?localStorage.setItem("leave-checked",1):localStorage.removeItem("leave-checked")}var c=$APP.p($APP.qs(new $APP.I(null,1,5,$APP.J,[lT],null))),d=$APP.G(c);c=$APP.E.j(d,mT);var e=$APP.E.j(d,$APP.qD),k=$APP.E.j(d,$APP.AC);d=$APP.E.j(d,
nT);var l=$APP.p($APP.qs(new $APP.I(null,1,5,$APP.J,[jT],null)));return new $APP.I(null,3,5,$APP.J,[oT,new $APP.I(null,2,5,$APP.J,[iT,"/images/guild-avatar.png"],null),new $APP.I(null,6,5,$APP.J,[pT,new $APP.I(null,3,5,$APP.J,[qT,new $APP.I(null,2,5,$APP.J,[rT,c],null),new $APP.I(null,3,5,$APP.J,[$APP.eo,new $APP.g(null,3,[$APP.Yn,$APP.EI,$APP.Zn,"text-base ml-12",$APP.us,function(){return $APP.h(localStorage.getItem("leave-checked"))?a():$APP.Q(new $APP.I(null,9,5,$APP.J,[$APP.vs,$APP.Fs,!0,$APP.Ns,
"",$APP.Hs,new $APP.I(null,4,5,$APP.J,[$APP.As,new $APP.I(null,2,5,$APP.J,[$APP.Oz,"Notice: Since you are trying to leave your current Kingdom, 10% of your Glony will be charged as handling fee. And 24h cool-down will be applied. Click Confirm to proceed."],null),new $APP.I(null,1,5,$APP.J,[$APP.Cs],null),new $APP.I(null,1,5,$APP.J,[kT],null)],null),$APP.Is,new $APP.I(null,3,5,$APP.J,[$APP.As,new $APP.I(null,3,5,$APP.J,[$APP.eo,new $APP.g(null,3,[$APP.Yn,$APP.BJ,$APP.us,$APP.Eu.j(a,b),$APP.Zn,"mr-8"],
null),"Confirm"],null),new $APP.I(null,3,5,$APP.J,[$APP.eo,new $APP.g(null,2,[$APP.Yn,$APP.Js,$APP.us,function(){return $APP.Q(new $APP.I(null,3,5,$APP.J,[$APP.vs,$APP.ws,!0],null))}],null),"Cancel"],null)],null)],null))}],null),"Leave My Kingdom"],null)],null),new $APP.I(null,3,5,$APP.J,[$APP.Bs,new $APP.I(null,2,5,$APP.J,[$APP.Bs,e],null),new $APP.I(null,2,5,$APP.J,[sT,"/ Members"],null)],null),new $APP.I(null,3,5,$APP.J,[$APP.Bs,new $APP.I(null,2,5,$APP.J,[$APP.Bs,["No.",$APP.n.o(k)].join("")],
null),new $APP.I(null,2,5,$APP.J,[sT,"/ Rank"],null)],null),new $APP.I(null,3,5,$APP.J,[$APP.Bs,new $APP.I(null,2,5,$APP.J,[$APP.Bs,"XXX"],null),new $APP.I(null,2,5,$APP.J,[sT,"/ Locked"],null)],null),new $APP.I(null,3,5,$APP.J,[$APP.Bs,new $APP.I(null,2,5,$APP.J,[tT,$APP.zl(d)],null),new $APP.I(null,2,5,$APP.J,[sT,"/ Your Role"],null)],null)],null)],null)};
xT=function(){var a=$APP.p($APP.qs(new $APP.I(null,1,5,$APP.J,[lT],null)));a=$APP.G(a);a=$APP.E.j(a,$APP.Vu);return new $APP.I(null,4,5,$APP.J,[vT,new $APP.g(null,1,[$APP.On,new $APP.g(null,1,[$APP.Qn,"calc(100% - 180px - 3.5rem)"],null)],null),new $APP.I(null,2,5,$APP.J,[wT,"Member status"],null),new $APP.I(null,4,5,$APP.J,[$APP.Bz,new $APP.g(null,1,[$APP.On,new $APP.g(null,3,[$APP.Qn,"calc(100% - 2.25rem)",$APP.$D,"2px ridge #50929e33",$APP.vv,"linear-gradient(\n    rgba(163, 218, 226, 0.1),\n    rgba(163, 218, 226, 0.05)\n  )"],
null)],null),new $APP.I(null,5,5,$APP.J,[$APP.oI,new $APP.g(null,1,[$APP.On,new $APP.g(null,1,[$APP.aB,"1fr 3fr 1fr"],null)],null),new $APP.I(null,2,5,$APP.J,[$APP.Bs,"ROLE"],null),new $APP.I(null,2,5,$APP.J,[$APP.Bs,"ADDRESS"],null),new $APP.I(null,2,5,$APP.J,[$APP.Bs,"RUNE LOCKED"],null)],null),new $APP.I(null,3,5,$APP.J,[$APP.ej,new $APP.I(null,2,5,$APP.J,[$APP.jJ,new $APP.g(null,1,[$APP.On,new $APP.g(null,1,[$APP.Qn,"calc(100% - 1.625rem)"],null)],null)],null),$APP.jj(function(b,c){return new $APP.I(null,
3,5,$APP.J,[gT,b,c],null)},a)],null)],null)],null)};$APP.qM=function(){return new $APP.I(null,4,5,$APP.J,[$APP.bA,new $APP.I(null,1,5,$APP.J,[uT],null),new $APP.I(null,1,5,$APP.J,[$APP.NL],null),new $APP.I(null,1,5,$APP.J,[xT],null)],null)};$APP.f=bT.prototype;$APP.f.Qa=function(){return $APP.p(this.Ra)};
$APP.f.call=function(a){switch(arguments.length-1){case 0:return this.D();case 1:return this.o(arguments[1]);case 2:return this.j(arguments[1],arguments[2]);default:throw Error(["Invalid arity: ",$APP.n.o(arguments.length-1)].join(""));}};$APP.f.apply=function(a,b){return this.call.apply(this,[this].concat($APP.bf(b)))};$APP.f.D=function(){var a=$APP.p(this.Ra);$APP.sg(this.Ra,$APP.p(this.Ra)+1);return a};$APP.f.o=function(a){var b=$APP.p(this.Ra);$APP.sg(this.Ra,$APP.p(this.Ra)+a);return b};
$APP.f.j=function(a,b){a=a instanceof $APP.qi?a.ka:null;switch(a){case "add":return $APP.sg(this.Ra,$APP.p(this.Ra)+b),null;case "set":return $APP.sg(this.Ra,b),null;case "set\x3d":case "set-get":return $APP.sg(this.Ra,b);case "\x3dset":case "get-set":return a=$APP.p(this.Ra),$APP.sg(this.Ra,b),a;case "\x3d+":case "get-add":return a=$APP.p(this.Ra),$APP.sg(this.Ra,$APP.p(this.Ra)+b),a;case "+\x3d":case "add-get":return $APP.sg(this.Ra,$APP.p(this.Ra)+b);default:throw Error(["No matching clause: ",
$APP.n.o(a)].join(""));}};
var nT=$APP.N("role"),mT=$APP.N("kname"),oT=$APP.N("div.flex.pt-9.pb-4.pl-11"),jT=$APP.O("fgl.app.views.guild-basic","leave-checked"),sT=$APP.N("span.text-xl.text-C74bfcee6"),tT=$APP.N("span.capitalize"),hT=$APP.N("img.w-36.mr-12"),lT=$APP.O("fgl.app.views.guild-basic","data"),vT=$APP.N("div.px-6.py-4"),yT=$APP.N("elder"),wT=$APP.N("div.text-xl.mb-2"),qT=$APP.N("div.flexrr.col-span-full"),pT=$APP.N("div.grid.grid-cols-3.text-3xl.gap-x-8.items-center"),zT=$APP.N("senator"),rT=$APP.N("span.text-4xl.col-span-full"),
fT=$APP.N("div.grid.justify-items-center.pb-1.border-b.border-C79c5da.pt-1_125rem.fp.font-bold");$APP.cd("guild-basic");$APP.pM={};
$APP.os(lT,$APP.A([function(a,b){$APP.D(b,0,null);b=$APP.G(a);b=$APP.E.j(b,$APP.Qu);var c=$APP.E.j(a,$APP.Zu);a=$APP.Nj(a,new $APP.I(null,2,5,$APP.J,[b,$APP.Yu],null),0);var d=eT(function(m,r,u){u=$APP.D(u,0,null);return $APP.K.A(m,u,r+1)},$APP.Ti,$APP.Sh($APP.nh,function(m,r){try{return m.gt(r)}catch(u){if(u instanceof Error)return!0;throw u;}},$APP.uj.j(function(m){var r=$APP.D(m,0,null);m=$APP.D(m,1,null);return new $APP.I(null,2,5,$APP.J,[r,$APP.VI.o($APP.h(m)?m:$APP.sn.BigNumber.from(1))],null)},
c)));c=$APP.E.j(c,a);var e=$APP.G(c);c=$APP.E.j(e,$APP.Vu);var k=$APP.E.j(e,$APP.Wu),l=$APP.E.j(e,$APP.qD);e=$APP.E.j(e,$APP.P);k=$APP.h($APP.cj($APP.vl([b]),c))?yT:$APP.h($APP.cj($APP.vl([b]),k))?zT:$APP.rJ;return new $APP.g(null,6,[$APP.YB,b,$APP.AC,$APP.E.j(d,a),nT,k,mT,e,$APP.Vu,c,$APP.qD,function(){var m=$APP.h(l)?l.toNumber():l;return $APP.h(m)?m:0}()],null)}]));$APP.NK.j(jT,function(a,b){$APP.D(b,0,null);b=$APP.D(b,1,null);return $APP.K.A(a,jT,b)});
$APP.os(jT,$APP.A([function(a){return $APP.E.A(a,jT,!1)}]));$APP.Je();
}).call(this);