(function(){
'use strict';var wN,xN,yN,zN,CN,EN,GN,QN,TN;wN=function(a){this.Sa=a;this.F=32769;this.P=0};xN=function(){return new wN($APP.lj(0))};yN=function(a){switch(arguments.length){case 0:return xN();case 1:return new wN($APP.lj(arguments[0]));default:throw Error(["Invalid arity: ",$APP.m.o(arguments.length)].join(""));}};zN=function(a,b,c){var d=xN?xN():yN.call(null);return $APP.cf.A(function(f,k){var l=d.D?d.D():d.call(null);return a.A?a.A(f,l,k):a.call(null,f,l,k)},b,c)};
$APP.AN=function(){return new $APP.H(null,1,5,$APP.I,[new $APP.g(null,2,[$APP.pp,function(){$APP.P(new $APP.H(null,1,5,$APP.I,[$APP.Py],null));$APP.P(new $APP.H(null,2,5,$APP.I,[$APP.xE,!0],null));$APP.P(new $APP.H(null,1,5,$APP.I,[$APP.ZB],null));return $APP.P(new $APP.H(null,1,5,$APP.I,[$APP.eA],null))},$APP.iB,$APP.Zh],null)],null)};
CN=function(a,b){return $APP.Vo(new $APP.g(null,2,[$APP.BA,function(){return $APP.P(new $APP.H(null,2,5,$APP.I,[$APP.iw,b],null))},$APP.Fo,function(){var c=$APP.p($APP.dr(new $APP.H(null,2,5,$APP.I,[$APP.iw,b],null)));return $APP.uh(new $APP.H(null,5,5,$APP.I,[BN,new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.Ix,"1fr 3fr 1fr"],null)],null),new $APP.H(null,2,5,$APP.I,[$APP.us,"Elder"],null),new $APP.H(null,2,5,$APP.I,[$APP.us,b],null),new $APP.H(null,2,5,$APP.I,[$APP.rG,c],null)],null),new $APP.g(null,
1,[$APP.Vm,a],null))}],null))};EN=function(a){return new $APP.H(null,2,5,$APP.I,[DN,new $APP.g(null,1,[$APP.R,a],null)],null)};
GN=function(){var a=$APP.p($APP.dr(new $APP.H(null,1,5,$APP.I,[FN],null)));return new $APP.H(null,3,5,$APP.I,[$APP.aH,new $APP.g(null,2,[$APP.$r,"1.5rem",$APP.TC,new $APP.H(null,2,5,$APP.I,[$APP.my,"Do not remind me next time"],null)],null),new $APP.g(null,3,[$APP.Po,"flex flex-row cs1 ce2 rs1 re2 justify-self-start items-center",$APP.yD,function(b){return $APP.P(new $APP.H(null,2,5,$APP.I,[FN,b],null))},$APP.xD,a],null)],null)};
QN=function(){function a(){return $APP.P(new $APP.H(null,2,5,$APP.I,[$APP.OE,new $APP.g(null,3,[$APP.pu,$APP.jz,$APP.Gs,"Leave Kingdom",$APP.su,function(){$APP.Ds();return $APP.P(new $APP.H(null,2,5,$APP.I,[$APP.xE,!0],null))}],null)],null))}function b(){return $APP.h(l)?localStorage.setItem("leave-checked",1):localStorage.removeItem("leave-checked")}var c=$APP.p($APP.dr(new $APP.H(null,1,5,$APP.I,[HN],null))),d=$APP.E(c);c=$APP.C.j(d,IN);var f=$APP.C.j(d,$APP.Hz),k=$APP.C.j(d,$APP.Zy);d=$APP.C.j(d,
JN);var l=$APP.p($APP.dr(new $APP.H(null,1,5,$APP.I,[FN],null)));return new $APP.H(null,3,5,$APP.I,[KN,new $APP.H(null,2,5,$APP.I,[EN,"/images/guild-avatar.png"],null),new $APP.H(null,6,5,$APP.I,[LN,new $APP.H(null,3,5,$APP.I,[MN,new $APP.H(null,2,5,$APP.I,[NN,c],null),new $APP.H(null,3,5,$APP.I,[$APP.js,new $APP.g(null,3,[$APP.es,$APP.uE,$APP.Po,"text-base ml-12",$APP.ns,function(){return $APP.h(localStorage.getItem("leave-checked"))?a():$APP.P(new $APP.H(null,9,5,$APP.I,[$APP.os,$APP.ys,!0,$APP.Gs,
"",$APP.As,new $APP.H(null,4,5,$APP.I,[$APP.BB,new $APP.H(null,2,5,$APP.I,[$APP.aC,"Notice: Since you are trying to leave your current Kingdom, 10% of your Glony will be charged as handling fee. And 24h cool-down will be applied. Click Confirm to proceed."],null),new $APP.H(null,1,5,$APP.I,[$APP.vs],null),new $APP.H(null,1,5,$APP.I,[GN],null)],null),$APP.Bs,new $APP.H(null,3,5,$APP.I,[$APP.BB,new $APP.H(null,3,5,$APP.I,[$APP.js,new $APP.g(null,3,[$APP.es,$APP.kF,$APP.ns,$APP.xu.j(a,b),$APP.Po,"mr-8"],
null),"Confirm"],null),new $APP.H(null,3,5,$APP.I,[$APP.js,new $APP.g(null,2,[$APP.es,$APP.Cs,$APP.ns,function(){return $APP.P(new $APP.H(null,3,5,$APP.I,[$APP.os,$APP.ps,!0],null))}],null),"Cancel"],null)],null)],null))}],null),"Leave My Kingdom"],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.us,new $APP.H(null,2,5,$APP.I,[$APP.us,f],null),new $APP.H(null,2,5,$APP.I,[ON,"/ Members"],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.us,new $APP.H(null,2,5,$APP.I,[$APP.us,["No.",$APP.m.o(k)].join("")],
null),new $APP.H(null,2,5,$APP.I,[ON,"/ Rank"],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.us,new $APP.H(null,2,5,$APP.I,[$APP.us,"XXX"],null),new $APP.H(null,2,5,$APP.I,[ON,"/ Locked"],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.us,new $APP.H(null,2,5,$APP.I,[PN,$APP.pl(d)],null),new $APP.H(null,2,5,$APP.I,[ON,"/ Your Role"],null)],null)],null)],null)};
TN=function(){var a=$APP.p($APP.dr(new $APP.H(null,1,5,$APP.I,[HN],null)));a=$APP.E(a);a=$APP.C.j(a,$APP.Ou);return new $APP.H(null,4,5,$APP.I,[RN,new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.Yr,"calc(100% - 180px - 3.5rem)"],null)],null),new $APP.H(null,2,5,$APP.I,[SN,"Member status"],null),new $APP.H(null,4,5,$APP.I,[$APP.zw,new $APP.g(null,1,[$APP.Q,new $APP.g(null,3,[$APP.Yr,"calc(100% - 2.25rem)",$APP.lA,"2px ridge #50929e33",$APP.wr,"linear-gradient(\n    rgba(163, 218, 226, 0.1),\n    rgba(163, 218, 226, 0.05)\n  )"],
null)],null),new $APP.H(null,5,5,$APP.I,[$APP.eE,new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.Ix,"1fr 3fr 1fr"],null)],null),new $APP.H(null,2,5,$APP.I,[$APP.us,"ROLE"],null),new $APP.H(null,2,5,$APP.I,[$APP.us,"ADDRESS"],null),new $APP.H(null,2,5,$APP.I,[$APP.us,"sGLORY LOCKED"],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.cj,new $APP.H(null,2,5,$APP.I,[$APP.VE,new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.Yr,"calc(100% - 1.625rem)"],null)],null)],null),$APP.fj(function(b,c){return new $APP.H(null,
3,5,$APP.I,[CN,b,c],null)},a)],null)],null)],null)};$APP.UN=function(){return new $APP.H(null,4,5,$APP.I,[$APP.Vw,new $APP.H(null,1,5,$APP.I,[QN],null),new $APP.H(null,1,5,$APP.I,[$APP.vH],null),new $APP.H(null,1,5,$APP.I,[TN],null)],null)};$APP.e=wN.prototype;$APP.e.Ra=function(){return $APP.p(this.Sa)};
$APP.e.call=function(a){switch(arguments.length-1){case 0:return this.D();case 1:return this.o(arguments[1]);case 2:return this.j(arguments[1],arguments[2]);default:throw Error(["Invalid arity: ",$APP.m.o(arguments.length-1)].join(""));}};$APP.e.apply=function(a,b){return this.call.apply(this,[this].concat($APP.bf(b)))};$APP.e.D=function(){var a=$APP.p(this.Sa);$APP.rg(this.Sa,$APP.p(this.Sa)+1);return a};$APP.e.o=function(a){var b=$APP.p(this.Sa);$APP.rg(this.Sa,$APP.p(this.Sa)+a);return b};
$APP.e.j=function(a,b){a=a instanceof $APP.oi?a.ka:null;switch(a){case "add":return $APP.rg(this.Sa,$APP.p(this.Sa)+b),null;case "set":return $APP.rg(this.Sa,b),null;case "set\x3d":case "set-get":return $APP.rg(this.Sa,b);case "\x3dset":case "get-set":return a=$APP.p(this.Sa),$APP.rg(this.Sa,b),a;case "\x3d+":case "get-add":return a=$APP.p(this.Sa),$APP.rg(this.Sa,$APP.p(this.Sa)+b),a;case "+\x3d":case "add-get":return $APP.rg(this.Sa,$APP.p(this.Sa)+b);default:throw Error(["No matching clause: ",
$APP.m.o(a)].join(""));}};
var JN=$APP.K("role"),IN=$APP.K("kname"),KN=$APP.K("div.flex.pt-9.pb-4.pl-11"),FN=$APP.M("fgl.app.views.guild-basic","leave-checked"),ON=$APP.K("span.text-xl.text-C74bfcee6"),PN=$APP.K("span.capitalize"),DN=$APP.K("img.w-36.mr-12"),HN=$APP.M("fgl.app.views.guild-basic","data"),RN=$APP.K("div.px-6.py-4"),VN=$APP.K("elder"),SN=$APP.K("div.text-xl.mb-2"),MN=$APP.K("div.flexrr.col-span-full"),LN=$APP.K("div.grid.grid-cols-3.text-3xl.gap-x-8.items-center"),WN=$APP.K("senator"),NN=$APP.K("span.text-4xl.col-span-full"),
BN=$APP.K("div.grid.justify-items-center.pb-1.border-b.border-C79c5da.pt-1_125rem.fp.font-bold");$APP.cd("guild-basic");$APP.XN={};
$APP.br(HN,$APP.y([function(a,b){$APP.A(b,0,null);b=$APP.E(a);b=$APP.C.j(b,$APP.Ju);var c=$APP.C.j(a,$APP.Su);a=$APP.Fj(a,new $APP.H(null,2,5,$APP.I,[b,$APP.Ru],null),0);var d=zN(function(n,r,v){v=$APP.A(v,0,null);return $APP.J.A(n,v,r+1)},$APP.Ri,$APP.Sh($APP.nh,function(n,r){try{return n.gt(r)}catch(v){if(v instanceof Error)return!0;throw v;}},$APP.nj.j(function(n){var r=$APP.A(n,0,null);n=$APP.A(n,1,null);return new $APP.H(null,2,5,$APP.I,[r,$APP.HE.o($APP.h(n)?n:$APP.Fr.BigNumber.from(1))],null)},
c)));c=$APP.C.j(c,a);var f=$APP.E(c);c=$APP.C.j(f,$APP.Ou);var k=$APP.C.j(f,$APP.Pu),l=$APP.C.j(f,$APP.Hz);f=$APP.C.j(f,$APP.O);k=$APP.h($APP.aj($APP.ll([b]),c))?VN:$APP.h($APP.aj($APP.ll([b]),k))?WN:$APP.aF;return new $APP.g(null,6,[$APP.Ay,b,$APP.Zy,$APP.C.j(d,a),JN,k,IN,f,$APP.Ou,c,$APP.Hz,function(){var n=$APP.h(l)?l.toNumber():l;return $APP.h(n)?n:0}()],null)}]));$APP.xF.j(FN,function(a,b){$APP.A(b,0,null);b=$APP.A(b,1,null);return $APP.J.A(a,FN,b)});
$APP.br(FN,$APP.y([function(a){return $APP.C.A(a,FN,!1)}]));$APP.Je();
}).call(this);