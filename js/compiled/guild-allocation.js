(function(){
'use strict';var UP,VP,nQ,bQ,aQ,fQ,iQ,hQ,WP,YP,dQ,lQ,kQ,gQ,XP,eQ,QP,SP,TP,oQ,$P,jQ,cQ,ZP,RP;$APP.PP=function(){return new $APP.H(null,1,5,$APP.I,[new $APP.g(null,2,[$APP.Cp,$APP.Zh,$APP.bB,$APP.Zh],null)],null)};
UP=function(){var a=$APP.p($APP.qr(new $APP.H(null,1,5,$APP.I,[QP],null)));a=$APP.E(a);a=$APP.C.j(a,RP);return new $APP.H(null,2,5,$APP.I,[SP,new $APP.g(null,2,[$APP.AC,function(b){return $APP.P(new $APP.H(null,2,5,$APP.I,[TP,b.target.value],null))},$APP.wz,[$APP.m.o(a)," %"].join("")],null)],null)};
VP=function(){var a=$APP.p($APP.qr(new $APP.H(null,1,5,$APP.I,[QP],null)));a=$APP.E(a);a=$APP.C.j(a,$APP.wz);return new $APP.H(null,3,5,$APP.I,[$APP.gs,new $APP.g(null,2,[$APP.bs,$APP.DE,$APP.gB,$APP.We(a)],null),"CONFIRM"],null)};
$APP.mQ=function(){return new $APP.H(null,7,5,$APP.I,[WP,new $APP.H(null,6,5,$APP.I,[XP,new $APP.H(null,3,5,$APP.I,[YP,new $APP.H(null,2,5,$APP.I,[ZP,"Weekly Virtue amount"],null),new $APP.H(null,3,5,$APP.I,[$P,new $APP.H(null,2,5,$APP.I,[$APP.QG,"2rem"],null),"360"],null)],null),new $APP.H(null,1,5,$APP.I,[aQ],null),new $APP.H(null,3,5,$APP.I,[YP,new $APP.H(null,2,5,$APP.I,[ZP,"Virtue for you to allocate"],null),new $APP.H(null,3,5,$APP.I,[$P,new $APP.H(null,2,5,$APP.I,[$APP.QG,"2rem"],null),"88"],
null)],null),new $APP.H(null,1,5,$APP.I,[aQ],null),new $APP.H(null,3,5,$APP.I,[YP,new $APP.H(null,2,5,$APP.I,[ZP,"Next allocation"],null),new $APP.H(null,7,5,$APP.I,[bQ,"01",new $APP.H(null,2,5,$APP.I,[cQ," D"],null),"16",new $APP.H(null,2,5,$APP.I,[cQ," H"],null),"20",new $APP.H(null,2,5,$APP.I,[dQ," M"],null)],null)],null)],null),new $APP.H(null,2,5,$APP.I,[eQ,"Your reserved $VIRTUE percentage:"],null),new $APP.H(null,1,5,$APP.I,[UP],null),new $APP.H(null,1,5,$APP.I,[fQ],null),new $APP.H(null,3,
5,$APP.I,[gQ,new $APP.H(null,3,5,$APP.I,[hQ,new $APP.H(null,2,5,$APP.I,[iQ,"Your reserved $VIRTUE amount:"],null),new $APP.H(null,3,5,$APP.I,[jQ,new $APP.H(null,2,5,$APP.I,[$APP.QG,"2rem"],null),"18"],null)],null),new $APP.H(null,3,5,$APP.I,[hQ,new $APP.H(null,2,5,$APP.I,[iQ,"Everyone else will receive:"],null),new $APP.H(null,5,5,$APP.I,[jQ,new $APP.H(null,2,5,$APP.I,[$APP.QG,"2rem"],null),"2.33",new $APP.H(null,1,5,$APP.I,[kQ],null),new $APP.H(null,2,5,$APP.I,[lQ,"person"],null)],null)],null)],
null),new $APP.H(null,1,5,$APP.I,[VP],null)],null)};nQ=$APP.M("fgl.app.views.guild-allocation","value");bQ=$APP.K("span.guild-font-family.text-xl.mt-2");aQ=$APP.K("div.h-10.border-l.border-solid.border-C79c5da87.mt-2");fQ=$APP.K("div.border-t-2.border-solid.border-white.w-full.mt-14.mb-4");iQ=$APP.K("p.text-C6bc9db");hQ=$APP.K("div.bg-C81c6dd1a.rounded-sm");WP=$APP.K("div.relative.px-10.py-8.text-center.fb");YP=$APP.K("div.grow.flex.flex-col.items-center");dQ=$APP.K("span.text-base.ffd.text-C6bc9db");
lQ=$APP.K("span.ffd.text-base.text-C6bc9db99.relative.top-0.5");kQ=$APP.K("span.ffd.text-base.text-C6bc9db99.align-bottom");gQ=$APP.K("div.grid.grid-cols-2.gap-x-2.rounded-sm.mb-20");XP=$APP.K("div.flex.bg-C81c6dd1a.py-2.items-center");eQ=$APP.K("p.w-full.text-center.text-C6bc9db.mt-10.mb-3");QP=$APP.M("fgl.app.views.guild-allocation","data");SP=$APP.K("input.w-full.text-center.guild-box-shadow.py-3.5.guild-font-family.text-4xl.bg-C0d293180");TP=$APP.M("fgl.app.views.guild-allocation","percent");
oQ=$APP.M("fgl.app.views.guild-allocation","display");$P=$APP.K("span.guild-font-family.text-xl.mt-2.flexr");jQ=$APP.K("span.guild-font-family.text-4xl.inline-block.my-6.flexr");cQ=$APP.K("span.text-base.ffd.text-C6bc9db.mr-3");ZP=$APP.K("span.text-C6bc9db");RP=$APP.K("display");$APP.cd("guild-allocation");$APP.pQ={};
$APP.RF.j(TP,function(a,b){$APP.A(b,0,null);var c=$APP.A(b,1,null),d=$APP.E(a);b=$APP.C.j(d,nQ);d=$APP.C.j(d,oQ);c=("string"===typeof c?c:"").replaceAll(" ","").replaceAll("%","");c=$APP.h(".".startsWith())?["0",$APP.m.o(c)].join(""):c;if($APP.x.j("",c))var e=new $APP.H(null,2,5,$APP.I,[null,""],null);else{try{e=$APP.Cr.utils.parseUnits(c)}catch(k){if(k instanceof Error)e=!1;else throw k;}e=!1===e?new $APP.H(null,2,5,$APP.I,[b,d],null):100<parseFloat(c)?new $APP.H(null,2,5,$APP.I,[b,d],null):new $APP.H(null,
2,5,$APP.I,[parseFloat(c),c],null)}b=e;e=$APP.A(b,0,null);b=$APP.A(b,1,null);return $APP.J.B(a,nQ,e,$APP.y([oQ,b]))});$APP.or(QP,$APP.y([function(a){var b=$APP.E(a);a=$APP.C.j(b,nQ);b=$APP.C.j(b,oQ);return new $APP.g(null,2,[$APP.wz,a,RP,b],null)}]));$APP.Je();
}).call(this);