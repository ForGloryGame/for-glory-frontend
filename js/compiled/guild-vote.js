(function(){
'use strict';var $H,aI,zI,CI,EI,mJ,dJ,hI,kJ,HI,uI,YI,DI,hJ,qI,tI,vI,oJ,kI,UI,pJ,ZI,qJ,WI,gI,lJ,lI,xI,rJ,XI,dI,gJ,sJ,cI,GI,tJ,uJ,TI,jJ,bJ,iJ,nI,FI,fJ,VI,QI,MI,eI,$I,yI,OI,vJ,SI,wJ,JI,xJ,iI,yJ,BI,PI,zJ,II,AJ,aJ,NI,rI,KI,BJ,eJ,LI,fI,oI,wI,CJ,sI,DJ,EJ,pI,FJ,cJ,GJ,mI,AI,jI,RI;$H=function(a){var b=a.getDate(),c=a.getFullYear(),d=$APP.C.j($APP.dG,a.getMonth()+1),f=a.getHours(),k=12<f;f=k?f-12:f;a=a.getMinutes();return[$APP.m.o(d)," ",$APP.m.o(b),",",$APP.m.o(c),",",$APP.m.o(f),":",$APP.m.o(a),k?"PM":"AM"].join("")};
aI=function(a,b){$APP.Zt($APP.Xt($APP.Xt(fetch("https://hub.snapshot.org/graphql",$APP.zo(new $APP.g(null,4,[$APP.pu,$APP.ev,$APP.$u,$APP.av,$APP.bv,new $APP.g(null,1,["content-type","application/json"],null),$APP.fv,JSON.stringify($APP.zo(a))],null))),function(c){if($APP.h(c.ok)){c=[c,c.json];var d=c[1];return null!=d?d.call(c[0]):null}console.error("Error in response",c);throw Error(c);}),b),console.error)};
$APP.bI=function(){return new $APP.H(null,1,5,$APP.I,[new $APP.g(null,2,[$APP.pp,function(){return $APP.P(new $APP.H(null,1,5,$APP.I,[$APP.Py],null))},$APP.iB,$APP.Zh],null)],null)};
zI=function(a){return $APP.Vo(new $APP.g(null,2,[$APP.BA,function(){return $APP.h(a)?($APP.P(new $APP.H(null,2,5,$APP.I,[cI,a],null)),$APP.P(new $APP.H(null,2,5,$APP.I,[dI,a],null))):null},$APP.Fo,function(){var b=$APP.p($APP.dr(new $APP.H(null,2,5,$APP.I,[eI,a],null)));b=$APP.E(b);var c=$APP.C.j(b,fI);$APP.C.j(b,gI);var d=$APP.E(c);b=$APP.C.j(d,$APP.Gs);var f=$APP.C.j(d,hI);c=$APP.C.j(d,$APP.fv);var k=$APP.C.j(d,iI);d=$APP.C.j(d,gI);return $APP.uh(new $APP.H(null,5,5,$APP.I,[jI,new $APP.H(null,3,
5,$APP.I,[kI,new $APP.H(null,3,5,$APP.I,[lI,new $APP.H(null,2,5,$APP.I,[mI,b],null),new $APP.H(null,2,5,$APP.I,[nI,k],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.js,new $APP.g(null,3,[$APP.es,$APP.tE,$APP.Po,"justify-self-end",$APP.ns,function(){return $APP.P(new $APP.H(null,3,5,$APP.I,[$APP.Vu,$APP.XC,new $APP.g(null,1,[oI,a],null)],null))}],null),"Check detail"],null)],null),new $APP.H(null,4,5,$APP.I,[pI,new $APP.g(null,1,[$APP.ns,function(){return open($APP.Dr($APP.y(["/address/",f])))}],null),
new $APP.H(null,2,5,$APP.I,[qI,["by ",$APP.Er(f)].join("")],null),new $APP.H(null,2,5,$APP.I,[$APP.yu,new $APP.H(null,2,5,$APP.I,[rI,new $APP.g(null,1,[$APP.R,"/images/share.png"],null)],null)],null)],null),new $APP.H(null,3,5,$APP.I,[sI,new $APP.H(null,2,5,$APP.I,[tI,"DESCRIPTIONS"],null),new $APP.H(null,2,5,$APP.I,[uI,c],null)],null),new $APP.H(null,5,5,$APP.I,[vI,new $APP.H(null,2,5,$APP.I,[wI,new $APP.g(null,1,[$APP.R,"/images/votes-no.png"],null)],null),"NO",new $APP.H(null,2,5,$APP.I,[xI,d],
null),new $APP.H(null,2,5,$APP.I,[yI,"VOTES"],null)],null)],null),new $APP.g(null,1,[$APP.Vm,a],null))}],null))};
CI=function(){return $APP.Vo(new $APP.g(null,1,[$APP.Fo,function(){var a=$APP.p($APP.dr(new $APP.H(null,1,5,$APP.I,[$APP.Ru],null)));a=$APP.C.j($APP.WG,a);var b=$APP.p($APP.dr(new $APP.H(null,2,5,$APP.I,[AI,a],null)));b=$APP.E(b);b=$APP.C.j(b,$APP.Zx);return $APP.h(a)?($APP.P(new $APP.H(null,2,5,$APP.I,[$APP.wx,a],null)),new $APP.H(null,3,5,$APP.I,[$APP.cj,new $APP.H(null,1,5,$APP.I,[BI],null),$APP.nj.j(function(c){var d=$APP.E(c);c=$APP.C.j(d,$APP.Oo);d=$APP.C.j(d,$APP.Hy);return new $APP.H(null,
3,5,$APP.I,[zI,c,d],null)},b)],null)):null}],null))};EI=function(a,b){return new $APP.H(null,4,5,$APP.I,[DI,new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.Ix,"3fr 1fr"],null)],null),new $APP.H(null,2,5,$APP.I,[$APP.us,a],null),new $APP.H(null,2,5,$APP.I,[$APP.us,b],null)],null)};
mJ=function(a){return $APP.Vo(new $APP.g(null,2,[$APP.BA,function(){return $APP.h(a)?($APP.P(new $APP.H(null,2,5,$APP.I,[cI,a],null)),$APP.P(new $APP.H(null,2,5,$APP.I,[dI,a],null))):null},$APP.Fo,function(){var b=$APP.p($APP.dr(new $APP.H(null,2,5,$APP.I,[eI,a],null)));b=$APP.E(b);var c=$APP.C.j(b,fI);b=$APP.C.j(b,gI);var d=$APP.E(c),f=$APP.C.j(d,$APP.Np);c=$APP.C.j(d,$APP.fv);var k=$APP.C.j(d,$APP.pp),l=$APP.C.j(d,gI),n=$APP.C.j(d,iI),r=$APP.C.j(d,FI),v=$APP.C.j(d,$APP.Gs),w=$APP.C.j(d,GI),z=$APP.C.j(d,
hI);d=$APP.C.j(d,$APP.yx);var B=$APP.p($APP.dr(new $APP.H(null,2,5,$APP.I,[HI,a],null)));B=$APP.E(B);var D=$APP.C.j(B,II);k=$APP.h(k)?$H(new Date(1E3*k)):k;f=$APP.h(f)?$H(new Date(1E3*f)):f;B=$APP.nj.j(II,b);var G=$APP.gh(B),N=$APP.gh($APP.uj(function(Y){return $APP.x.j(1,Y)},B)),S=G-N;return new $APP.H(null,3,5,$APP.I,[JI,new $APP.H(null,2,5,$APP.I,[$APP.Q,"\n      .vote-desc {\n        height: calc(100% - 1.75rem);\n      }\n\n"],null),new $APP.H(null,2,5,$APP.I,[KI,new $APP.H(null,5,5,$APP.I,[LI,
new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.Ix,"1fr 2.8fr"],null)],null),new $APP.H(null,4,5,$APP.I,[MI,new $APP.H(null,3,5,$APP.I,[NI,new $APP.H(null,2,5,$APP.I,[OI,v],null),new $APP.H(null,3,5,$APP.I,[$APP.Ey,new $APP.g(null,1,[$APP.ns,function(){return $APP.P(new $APP.H(null,2,5,$APP.I,[$APP.Vu,$APP.zB],null))}],null),new $APP.H(null,2,5,$APP.I,[$APP.qC,new $APP.g(null,1,[$APP.R,"/images/eject.png"],null)],null)],null)],null),new $APP.H(null,3,5,$APP.I,[PI,new $APP.H(null,2,5,$APP.I,[QI,
["by ",$APP.Er(z)].join("")],null),new $APP.H(null,2,5,$APP.I,[RI,new $APP.g(null,1,[$APP.Po,"bg-url(/images/link.png)"],null)],null)],null),new $APP.H(null,3,5,$APP.I,[SI,new $APP.H(null,3,5,$APP.I,[TI,new $APP.H(null,2,5,$APP.I,[UI,"descriptions:"],null),c],null),function(){var Y=$APP.x.j(n,"active");return Y?new $APP.H(null,3,5,$APP.I,[VI,new $APP.H(null,3,5,$APP.I,[$APP.Vw,new $APP.H(null,3,5,$APP.I,[WI,new $APP.g(null,2,[$APP.Po,$APP.x.j(D,1)?"bg-C71edf59c":"bg-Cffffff26 text-Cb2f4fa",$APP.ns,
function(){return $APP.P(new $APP.H(null,3,5,$APP.I,[XI,a,1],null))}],null),$APP.t(r)],null),new $APP.H(null,3,5,$APP.I,[YI,new $APP.g(null,2,[$APP.Po,$APP.x.j(D,2)?"bg-C71edf59c":"bg-Cffffff26 text-Cb2f4fa",$APP.ns,function(){return $APP.P(new $APP.H(null,3,5,$APP.I,[XI,a,2],null))}],null),$APP.nh(r)],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.js,new $APP.g(null,3,[$APP.es,$APP.jF,$APP.Po,"text-white",$APP.ns,function(){return $APP.P(new $APP.H(null,3,5,$APP.I,[ZI,a,D],null))}],null),"VOTE"],
null)],null):Y}()],null)],null),new $APP.H(null,8,5,$APP.I,[$I,new $APP.H(null,2,5,$APP.I,[aJ,"MESSAGE"],null),new $APP.H(null,10,5,$APP.I,[bJ,new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.Ix,"max-content 1fr"],null)],null),new $APP.H(null,2,5,$APP.I,[cJ,"IPFS"],null),new $APP.H(null,2,5,$APP.I,[dJ,w],null),new $APP.H(null,2,5,$APP.I,[cJ,"Start date"],null),new $APP.H(null,2,5,$APP.I,[dJ,k],null),new $APP.H(null,2,5,$APP.I,[cJ,"End date"],null),new $APP.H(null,2,5,$APP.I,[dJ,f],null),new $APP.H(null,
2,5,$APP.I,[cJ,"Snapshot"],null),new $APP.H(null,2,5,$APP.I,[dJ,d],null)],null),new $APP.H(null,2,5,$APP.I,[eJ,"THE CURRENT RESULT"],null),new $APP.H(null,4,5,$APP.I,[fJ,new $APP.H(null,2,5,$APP.I,[$APP.us,$APP.t(r)],null),function(){var Y=0<G;return Y?(Y=N>S)?new $APP.H(null,2,5,$APP.I,[$APP.us,"The current result"],null):Y:Y}(),function(){var Y=0<G;return Y?(Y=N<S)?new $APP.H(null,2,5,$APP.I,[$APP.us,[$APP.m.o(N),1<N?" Votes ":" Vote ",0<G?$APP.m.o(N/G*100):"0","%"].join("")],null):Y:Y}()],null),
new $APP.H(null,2,5,$APP.I,[gJ,new $APP.H(null,2,5,$APP.I,[hJ,new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.$r,0<G?[$APP.m.o(N/G*100),"%"].join(""):0],null)],null)],null)],null),new $APP.H(null,4,5,$APP.I,[fJ,new $APP.H(null,2,5,$APP.I,[$APP.us,$APP.nh(r)],null),function(){var Y=0<G;return Y?(Y=S>N)?new $APP.H(null,2,5,$APP.I,[$APP.us,"The current result"],null):Y:Y}(),function(){var Y=0<G;return Y?(Y=S<N)?new $APP.H(null,2,5,$APP.I,[$APP.us,[$APP.m.o(S),1<S?" Votes ":" Vote ",0<G?$APP.m.o(S/
G*100):"0","%"].join("")],null):Y:Y}()],null),new $APP.H(null,2,5,$APP.I,[iJ,new $APP.H(null,2,5,$APP.I,[hJ,new $APP.g(null,1,[$APP.Q,new $APP.g(null,1,[$APP.$r,0<G?["",$APP.m.o(S/G*100),"%"].join(""):0],null)],null)],null)],null)],null),new $APP.H(null,3,5,$APP.I,[$APP.cD,new $APP.H(null,2,5,$APP.I,[jJ,["VOTE (",$APP.m.o(l),")"].join("")],null),new $APP.H(null,3,5,$APP.I,[$APP.cj,new $APP.H(null,1,5,$APP.I,[kJ],null),$APP.Aj(function(Y){var da=$APP.E(Y);Y=$APP.C.j(da,lJ);var pa=$APP.C.j(da,GI);da=
$APP.C.j(da,II);return new $APP.H(null,4,5,$APP.I,[EI,Y,$APP.hh(r,da-1),pa],null)},b)],null)],null)],null)],null)],null)}],null))};$APP.nJ=function(a){a=$APP.E(a);a=$APP.C.j(a,$APP.Fy);a=$APP.E(a);a=$APP.C.j(a,oI);return $APP.h(a)?new $APP.H(null,2,5,$APP.I,[mJ,a],null):new $APP.H(null,1,5,$APP.I,[CI],null)};dJ=$APP.K("span.justify-self-end");hI=$APP.K("author");kJ=$APP.K("div.flex-1.guild-table-body.text-sm.overflow-auto");HI=$APP.M("fgl.app.views.guild-vote","pdata");uI=$APP.K("p.ffd.text-base");
YI=$APP.K("button.guild-font-family.text-sm.tracking-wider.rounded.px-16.py-0_5.ml-2");DI=$APP.K("div.grid.py-3.justify-items-center.border-b-1px.border-solid.border-C79c5da");hJ=$APP.K("div.absolute.h-1_5.rounded-l-full.bg-C96a1ae");qI=$APP.K("span.block.mr-2");tI=$APP.K("h3.text-xl");vI=$APP.K("div.flexrr.justify-end.mt-2");oJ=$APP.M("fgl.app.snapshot","set");kI=$APP.K("div.flexrr.justify-between");UI=$APP.K("span.uppercase.pr-2");pJ=$APP.K("domain");ZI=$APP.M("fgl.app.views.guild-vote","vote");
qJ=$APP.K("variables");WI=$APP.K("button.guild-font-family.text-sm.tracking-wider.rounded.px-16.py-0_5");gI=$APP.K("votes");lJ=$APP.K("voter");lI=$APP.K("div.flexrr.items-center");xI=$APP.K("span.text-Cd6d6d6.mx-1");rJ=$APP.K("operationName");XI=$APP.M("fgl.app.views.guild-vote","choice");dI=$APP.M("fgl.app.snapshot","votes");gJ=$APP.K("div.relative.w-full.h-1_5.rounded-l-full.bg-C00000033.mb-4");sJ=$APP.K("metadata");cI=$APP.M("fgl.app.snapshot","proposal");GI=$APP.K("ipfs");tJ=$APP.K("space");
uJ=$APP.K("timestamp");TI=$APP.K("div.vote-desc.overflow-auto.text-sm");jJ=$APP.K("div.rounded-sm.bg-C79c5da66.text-lg.text-center");bJ=$APP.K("p.grid.gap-y-1");iJ=$APP.K("div.relative.w-full.h-1_5.rounded-l-full.bg-C00000033");nI=$APP.K("div.bg-Ced8e28.px-12.rounded-md.text-base.capitalize");FI=$APP.K("choices");fJ=$APP.K("p.flex.justify-between.mb-1_5");VI=$APP.K("div.mt-1.flexb");QI=$APP.K("span.align-middle");MI=$APP.K("div.col-span-2");eI=$APP.M("fgl.app.views.guild-vote","data");$I=$APP.K("div.text-xs");
yI=$APP.K("span.text-Cd6d6d6");OI=$APP.K("p.guild-font-family.text-2xl.uppercase.tracking-wider");vJ=$APP.K("from");SI=$APP.K("div.bg-C81c6dd1a.rounded-sm.p-3.mt-2.text-Cd6d6d6.h-48");wJ=$APP.K("author_in");JI=$APP.K("div.py-6.px-8");xJ=$APP.K("orderDirection");iI=$APP.K("state");yJ=$APP.K("Vote");BI=$APP.K("div.overflow-y-auto");PI=$APP.K("p.text-xs.text-Cd6d6d6.pl-1");zJ=$APP.K("first");II=$APP.K("choice");AJ=$APP.K("sig");aJ=$APP.K("p.guild-font-family.text-base.mb-1");NI=$APP.K("div.flexb");
rI=$APP.K("img.w-3");KI=$APP.K("div.p-3.pb-3.bg-C81c6dd1a.h-full");BJ=$APP.K("skip");eJ=$APP.K("p.guild-font-family.text-base.mt-3.mb-1");LI=$APP.K("div.grid.grid-rows-2.gap-x-10.gap-y-8.h-full.pr-9");fI=$APP.K("proposal");oI=$APP.K("proposal-id");wI=$APP.K("img.w-2.mr-2");CJ=$APP.K("uint32");sI=$APP.K("div.bg-C81c6dd1a.p-4.text-Cd6d6d6.rounded-md.mt-4");DJ=$APP.K("types");EJ=$APP.K("orderBy");pI=$APP.K("div.flexrr.items-center.mt-4.text-Cd6d6d6.cursor-pointer");FJ=$APP.M("fgl.app.snapshot","proposals");
cJ=$APP.K("span.text-C96a1ae");GJ=$APP.K("bytes32");mI=$APP.K("span.text-4xl.mr-4.uppercase");AI=$APP.M("fgl.app.views.guild-vote","proposals");jI=$APP.K("div.bg-C81c6dd1a.rounded-md.p-4.fb.mx-8.my-4");RI=$APP.K("span.align-middle.inline-block.w-0_65rem.h-0_65rem.bg-no-repeat.bg-contain.bg-center");$APP.cd("guild-vote");$APP.wF.j(oJ,function(a,b){b=$APP.q(b);$APP.t(b);var c=$APP.u(b);b=$APP.t(c);c=$APP.u(c);return $APP.hp(a,c,b)});
$APP.Xq(cI,function(a,b){$APP.A(b,0,null);var c=$APP.A(b,1,null);aI(new $APP.g(null,3,[rJ,"Proposal",qJ,new $APP.g(null,1,[$APP.Oo,c],null),$APP.IE,"query Proposal($id: String!) {\nproposal(id: $id) {\nid\nipfs\ntitle\nbody\ndiscussion\nchoices\nstart\nend\nsnapshot\nstate\nauthor\ncreated\nplugins\nnetwork\ntype\nquorum\nsymbol\nstrategies {\nname\nnetwork\nparams\n}\nspace {\nid\nname\n}\nscores_state\nscores\nscores_by_strategy\nscores_total\nvotes\n}\n}"],null),function(d){return $APP.P(new $APP.H(null,
4,5,$APP.I,[oJ,$APP.bm(d.data.proposal,$APP.y([$APP.cm,!0])),cI,c],null))});return $APP.Ri});
$APP.Xq(FJ,function(a,b){$APP.A(b,0,null);var c=$APP.A(b,1,null);aI(new $APP.g(null,3,[rJ,"Proposals",qJ,new $APP.g(null,5,[zJ,6,BJ,0,tJ,$APP.h(c)?c:"test1234567.eth",iI,"all",wJ,$APP.F],null),$APP.IE,"query Proposals($first: Int!, $skip: Int!, $state: String!, $space: String, $space_in: [String], $author_in: [String]) {\nproposals(\nfirst: $first\nskip: $skip\nwhere: {space: $space, state: $state, space_in: $space_in, author_in: $author_in}\n) {\nid\nipfs\ntitle\nbody\nstart\nend\nstate\nauthor\ncreated\nchoices\nspace {\nid\nname\nmembers\navatar\nsymbol\n}\nscores_state\nscores_total\nscores\nvotes\nquorum\nsymbol\n}\n}"],null),
function(d){return $APP.P(new $APP.H(null,4,5,$APP.I,[oJ,$APP.bm(d.data.proposals,$APP.y([$APP.cm,!0])),FJ,$APP.h(c)?c:"test1234567.eth"],null))});return $APP.Ri});
$APP.Xq(dI,function(a,b){$APP.A(b,0,null);var c=$APP.A(b,1,null);aI(new $APP.g(null,3,[rJ,"Votes",qJ,new $APP.g(null,5,[$APP.Oo,c,EJ,"vp",xJ,"desc",zJ,1,lJ,""],null),$APP.IE,"query Votes($id: String!, $first: Int, $skip: Int, $orderBy: String, $orderDirection: OrderDirection, $voter: String) {\nvotes(\nfirst: $first\nskip: $skip\nwhere: {proposal: $id, vp_gt: 0, voter: $voter}\norderBy: $orderBy\norderDirection: $orderDirection\n) {\nipfs\nvoter\nchoice\nvp\nvp_by_strategy\n}\n}"],null),function(d){return $APP.P(new $APP.H(null,
4,5,$APP.I,[oJ,$APP.bm(d.data.votes,$APP.y([$APP.cm,!0])),dI,c],null))});return $APP.Ri});$APP.HJ={};$APP.br(eI,$APP.y([function(a,b){$APP.A(b,0,null);var c=$APP.A(b,1,null);b=$APP.Ej(a,new $APP.H(null,2,5,$APP.I,[cI,c],null));a=$APP.Ej(a,new $APP.H(null,2,5,$APP.I,[dI,c],null));return new $APP.g(null,2,[fI,b,gI,a],null)}]));$APP.br(AI,$APP.y([function(a,b){$APP.A(b,0,null);b=$APP.A(b,1,null);a=$APP.Ej(a,new $APP.H(null,2,5,$APP.I,[$APP.wx,b],null));return new $APP.g(null,1,[$APP.Zx,a],null)}]));
$APP.wF.j(XI,function(a,b){$APP.A(b,0,null);var c=$APP.A(b,1,null);b=$APP.A(b,2,null);return $APP.hp(a,new $APP.H(null,2,5,$APP.I,[XI,c],null),b)});$APP.br(HI,$APP.y([function(a,b){$APP.A(b,0,null);b=$APP.A(b,1,null);var c=$APP.E(a),d=$APP.C.j(c,XI);c=$APP.C.j(c,$APP.Ju);a=$APP.Ej(a,new $APP.H(null,2,5,$APP.I,[c,$APP.Ru],null));return new $APP.g(null,3,[II,$APP.C.A(d,b,1),$APP.nC,a,$APP.Ay,c],null)}]));
$APP.Xq(ZI,function(a,b){a=$APP.E(a);a=$APP.C.j(a,$APP.Qq);$APP.A(b,0,null);var c=$APP.A(b,1,null);b=$APP.A(b,2,null);a=$APP.E(a);var d=$APP.C.j(a,$APP.wu),f=$APP.C.j(a,$APP.Ju),k=new $APP.g(null,2,[$APP.O,"snapshot",$APP.yw,"0.1.4"],null),l=new $APP.g(null,1,[yJ,new $APP.H(null,6,5,$APP.I,[new $APP.g(null,2,[$APP.O,vJ,$APP.T,$APP.eu],null),new $APP.g(null,2,[$APP.O,tJ,$APP.T,$APP.tw],null),new $APP.g(null,2,[$APP.O,uJ,$APP.T,$APP.HD],null),new $APP.g(null,2,[$APP.O,fI,$APP.T,GJ],null),new $APP.g(null,
2,[$APP.O,II,$APP.T,CJ],null),new $APP.g(null,2,[$APP.O,sJ,$APP.T,$APP.tw],null)],null)],null),n=new $APP.g(null,7,[tJ,"test1234567.eth",vJ,f,$APP.T,"single-choice",II,b,uJ,parseInt((new Date).getTime()/1E3),fI,c,sJ,"{}"],null);$APP.lu($APP.Vt(null),function(){return $APP.lu($APP.Vt($APP.Fs()),function(){return $APP.lu($APP.Vt($APP.Zt(function(){var r=[d,d.getSigner];var v=r[1];r=null!=v?v.call(r[0]):null;r=[r,r._signTypedData];v=r[1];return null!=v?v.call(r[0],$APP.zo(k),$APP.zo(l),$APP.zo(n)):null}(),
function(){return $APP.Is(new $APP.g(null,2,[$APP.Gs,"Typed Sign Failed",$APP.As,"User rejected typed sign in wallet"],null))})),function(r){return $APP.lu($APP.Vt($APP.Zt($APP.Xt(fetch("https://hub.snapshot.org/api/msg",new $APP.g(null,4,[$APP.pu,$APP.ev,$APP.$u,$APP.av,$APP.bv,new $APP.g(null,1,["content-type","application/json"],null),$APP.fv,JSON.stringify($APP.zo(new $APP.g(null,3,[$APP.eu,f,AJ,r,$APP.uw,new $APP.g(null,3,[pJ,k,DJ,l,$APP.Yx,n],null)],null)))],null)),$APP.Yu),function(v){return $APP.P(new $APP.H(null,
7,5,$APP.I,[$APP.os,$APP.ys,!0,$APP.dz,!0,$APP.As,new $APP.H(null,5,5,$APP.I,[$APP.rs,new $APP.H(null,2,5,$APP.I,[$APP.us,"Message signed"],null),new $APP.H(null,1,5,$APP.I,[$APP.vs],null),new $APP.H(null,2,5,$APP.I,[$APP.us,"Failed to create proposal, please try again"],null),new $APP.H(null,2,5,$APP.I,[$APP.us,v.message],null)],null)],null))})),function(v){return $APP.lu($APP.Vt(null),function(){if($APP.h(v)){$APP.P(new $APP.H(null,2,5,$APP.I,[dI,c],null));var w=$APP.P(new $APP.H(null,7,5,$APP.I,
[$APP.os,$APP.ys,!0,$APP.dz,!0,$APP.As,new $APP.H(null,4,5,$APP.I,[$APP.rs,new $APP.H(null,2,5,$APP.I,[$APP.us,"Message signed"],null),new $APP.H(null,1,5,$APP.I,[$APP.vs],null),new $APP.H(null,2,5,$APP.I,[$APP.us,"Vote created"],null)],null)],null))}else w=null;return $APP.Vt(w)})})})})});return $APP.Ri});$APP.Je();
}).call(this);