(function(){
'use strict';var O5,N5,P5,M5,L5,U5,R5,K5,J5,Q5,S5,T5;$APP.A4=function(){return new $APP.O(null,1,5,$APP.Q,[new $APP.l(null,2,[$APP.gq,function(){$APP.cr(new $APP.O(null,2,5,$APP.Q,[$APP.Oz,$APP.$F.address],null));$APP.cr(new $APP.O(null,1,5,$APP.Q,[$APP.vA],null));$APP.cr(new $APP.O(null,1,5,$APP.Q,[$APP.Wv],null));return $APP.cr(new $APP.O(null,1,5,$APP.Q,[$APP.rw],null))},$APP.mA,$APP.li],null)],null)};
$APP.z4=function(){var a=$APP.Vf($APP.Xr(new $APP.O(null,1,5,$APP.Q,[J5],null))),b=$APP.gj(a);$APP.H.j(b,$APP.$B);var c=$APP.H.j(b,$APP.az);a=$APP.H.j(b,$APP.zx);var d=$APP.H.j(b,K5),e=$APP.H.j(b,L5);b=$APP.H.j(b,M5);return new $APP.O(null,8,5,$APP.Q,[N5,new $APP.O(null,2,5,$APP.Q,[$APP.xx,"Exchange Rate:"],null),new $APP.O(null,2,5,$APP.Q,[O5,new $APP.O(null,6,5,$APP.Q,[P5,new $APP.O(null,2,5,$APP.Q,[$APP.EF,"2rem"],null),new $APP.O(null,2,5,$APP.Q,[$APP.GC,"1"],null),new $APP.O(null,3,5,$APP.Q,
[$APP.SB,new $APP.O(null,2,5,$APP.Q,[$APP.rB,new $APP.l(null,1,[$APP.kD,"/images/ratio.svg"],null)],null),new $APP.O(null,2,5,$APP.Q,[$APP.rB,new $APP.l(null,2,[$APP.iu,new $APP.l(null,1,[$APP.mu,"rotate(180deg)"],null),$APP.kD,"/images/ratio.svg"],null)],null)],null),d,new $APP.O(null,3,5,$APP.Q,[$APP.OF,"2rem",new $APP.l(null,1,[$APP.vp,"ml-2"],null)],null)],null)],null),new $APP.O(null,2,5,$APP.Q,[$APP.xx,"Amount of $GLORY to exchange:"],null),new $APP.O(null,2,5,$APP.Q,[$APP.Wx,new $APP.l(null,
3,[$APP.Kv,function(f){return $APP.cr(new $APP.O(null,2,5,$APP.Q,[Q5,f.target.value],null))},$APP.Cy,a,$APP.iu,new $APP.l(null,1,[$APP.uu,"inset 1px 1px #111, 1px 1px rgba(116, 191, 206, 0.2)"],null)],null)],null),new $APP.O(null,2,5,$APP.Q,[$APP.DF,new $APP.l(null,1,[$APP.vp,"mt-14 mb-4"],null)],null),new $APP.O(null,3,5,$APP.Q,[R5,new $APP.O(null,3,5,$APP.Q,[$APP.pw,new $APP.O(null,2,5,$APP.Q,[S5,"You will receive:"],null),new $APP.O(null,3,5,$APP.Q,[$APP.tD,new $APP.O(null,2,5,$APP.Q,[$APP.OF,
"2.5rem"],null),new $APP.O(null,3,5,$APP.Q,[$APP.CF,e,new $APP.l(null,2,[$APP.oC,4,$APP.iu,new $APP.l(null,1,[$APP.xC,"20rem"],null)],null)],null)],null)],null),new $APP.O(null,3,5,$APP.Q,[$APP.pw,new $APP.O(null,2,5,$APP.Q,[S5,"You $GOLD Balance will be:"],null),new $APP.O(null,3,5,$APP.Q,[$APP.tD,new $APP.O(null,2,5,$APP.Q,[$APP.OF,"2.5rem"],null),new $APP.O(null,3,5,$APP.Q,[$APP.CF,b,new $APP.l(null,1,[$APP.iu,new $APP.l(null,1,[$APP.xC,"20rem"],null)],null)],null)],null)],null)],null),new $APP.O(null,
2,5,$APP.Q,[T5,new $APP.O(null,3,5,$APP.Q,[$APP.xu,new $APP.l(null,2,[$APP.zA,function(){return $APP.cr($APP.m(c)?$APP.li:new $APP.O(null,2,5,$APP.Q,[$APP.uz,new $APP.l(null,3,[$APP.Nt,$APP.ly,$APP.Ot,new $APP.O(null,2,5,$APP.Q,[$APP.$F.address,"0xffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"],null),$APP.Pt,function(){return $APP.cr(new $APP.O(null,2,5,$APP.Q,[$APP.Oz,$APP.$F.address],null))}],null)],null))},$APP.su,$APP.VD],null),$APP.m(c)?"CONFIRM":"APPROVE"],null)],null)],null)};
O5=$APP.S("div.w-full.text-xl.py-3.bg-C81c6dd1a.px-40");N5=$APP.S("div.relative.px-10.fb");P5=$APP.S("div.grid.grid-cols-5.items-center.m-auto.w-30%");M5=$APP.S("balance-rst");L5=$APP.S("receive");U5=$APP.T("fgl.app.views.merchant-swap","input-str");R5=$APP.S("div.grid.grid-cols-2.gap-4");K5=$APP.S("ratio");J5=$APP.T("fgl.app.views.merchant-swap","data");Q5=$APP.T("fgl.app.views.merchant-swap","input");S5=$APP.S("div.text-base.mt-4.mb-3.ffd.flexrs.text-C6bc9db");T5=$APP.S("div.w-full.flexr.mt-20");$APP.id("merchant-swap");$APP.y4={};
$APP.$E.j(Q5,function(a,b){$APP.G(b,0,null);b=$APP.G(b,1,null);var c=$APP.gj(a);c=$APP.H.j(c,$APP.Mz);c=$APP.Vj(a,new $APP.O(null,2,5,$APP.Q,[c,$APP.FC],null));b=b.replace(/\.+/,".").replace(/-/,"");try{var d=module$node_modules$ethers$lib$index.utils.parseUnits(b)}catch(f){if(f instanceof Error)d=$APP.yy;else throw f;}if($APP.B.j(b,""))a=$APP.R.B(a,Q5,module$node_modules$ethers$lib$index.BigNumber.from("0"),$APP.D([U5,""]));else if(!$APP.B.j(d,$APP.yy)||!$APP.Ma(b,".")||$APP.B.j($APP.en(b,"."),$APP.fn(b)))if($APP.B.j(d,
$APP.yy)&&$APP.Ma(b,"."))a=$APP.R.A(a,U5,b);else if(!$APP.B.j(d,$APP.yy)){var e=d.gte(0);c=$APP.m(e)?d.lte(c):e;a=$APP.m(c)?$APP.R.B(a,Q5,d,$APP.D([U5,b])):a}return a});
$APP.Vr(J5,$APP.D([function(a){var b=$APP.gj(a),c=$APP.H.j(b,$APP.gA),d=$APP.H.j(b,Q5),e=$APP.H.j(b,U5);b=$APP.H.j(b,$APP.Mz);var f=$APP.H.j(a,b);f=$APP.gj(f);f=$APP.H.j(f,$APP.Ex);f=$APP.m(f)?f:module$node_modules$ethers$lib$index.BigNumber.from("0");c=$APP.m(c)?c:module$node_modules$ethers$lib$index.BigNumber.from("1");d=$APP.m(d)?d:module$node_modules$ethers$lib$index.BigNumber.from("0");var h=d.div(c);a=$APP.Wj(a,new $APP.O(null,3,5,$APP.Q,[b,$APP.Oz,$APP.$F.address],null),module$node_modules$ethers$lib$index.BigNumber.from("0"));
return new $APP.l(null,6,[$APP.$B,d,$APP.az,d.lte(a),K5,1/c,$APP.zx,e,L5,h,M5,f.add(h)],null)}]));$APP.Se();
}).call(this);