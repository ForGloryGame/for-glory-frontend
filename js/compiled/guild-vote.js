(function(){
'use strict';var tM,sM,BM,CM,xM,DM,EM,AM,zM,yM,rM,vM,FM,GM,wM,uM,HM;tM=function(a,b){$APP.du($APP.bu($APP.bu(fetch("https://hub.snapshot.org/graphql",$APP.Np(new $APP.h(null,4,[$APP.uu,rM,$APP.UC,sM,$APP.nE,new $APP.h(null,1,["content-type","application/json"],null),$APP.SC,JSON.stringify($APP.Np(a))],null))),function(c){if($APP.k(c.ok)){c=[c,c.json];var d=c[1];return null!=d?d.call(c[0]):null}console.error("Error in response",c);throw Error(c);}),b),console.error)};
$APP.bL=function(){return new $APP.J(null,1,5,$APP.K,[new $APP.h(null,2,[$APP.Aq,$APP.ai,$APP.ov,$APP.ai],null)],null)};
$APP.aL=function(a){a=$APP.H(a);a=$APP.E.j(a,$APP.Bw);a=$APP.H(a);var b=$APP.E.j(a,uM);a=$APP.p($APP.os(new $APP.J(null,2,5,$APP.K,[vM,b],null)));a=$APP.H(a);var c=$APP.E.j(a,wM),d=$APP.E.j(a,xM);return $APP.xp(new $APP.h(null,2,[$APP.ED,function(){$APP.k(b)&&($APP.S(new $APP.J(null,2,5,$APP.K,[yM,b],null)),$APP.S(new $APP.J(null,2,5,$APP.K,[zM,b],null)));return $APP.S(new $APP.J(null,3,5,$APP.K,[$APP.av,$APP.bG,new $APP.h(null,1,[uM,"0x0eb23ea0b877666ad3ddcd0d7da0114acdfe5ae6390b5628b7509f4338022db5"],
null)],null))},$APP.Tp,function(){return new $APP.J(null,4,5,$APP.K,[$APP.Lz,"vote",new $APP.J(null,2,5,$APP.K,[AM,$APP.n.o(c)],null),new $APP.J(null,2,5,$APP.K,[AM,$APP.n.o(d)],null)],null)}],null),$APP.tJ)};sM=$APP.O("cors");BM=$APP.P("fgl.app.snapshot","set");CM=$APP.O("variables");xM=$APP.O("votes");DM=$APP.O("voter");EM=$APP.O("operationName");AM=$APP.O("pre");zM=$APP.P("fgl.app.snapshot","votes");yM=$APP.P("fgl.app.snapshot","proposal");rM=$APP.O("post");
vM=$APP.P("fgl.app.views.guild-vote","data");FM=$APP.O("orderDirection");GM=$APP.O("first");wM=$APP.O("proposal");uM=$APP.O("proposal-id");HM=$APP.O("orderBy");$APP.ed("guild-vote");$APP.AJ.j(BM,function(a,b){b=$APP.q(b);$APP.u(b);var c=$APP.v(b);b=$APP.u(c);c=$APP.v(c);return $APP.sq(a,c,b)});
$APP.hs(yM,function(a,b){$APP.C(b,0,null);var c=$APP.C(b,1,null);tM(new $APP.h(null,3,[EM,"Proposal",CM,new $APP.h(null,1,[$APP.bq,c],null),$APP.LH,"query Proposal($id: String!) {\n  proposal(id: $id) {\n    id\n    ipfs\n    title\n    body\n    discussion\n    choices\n    start\n    end\n    snapshot\n    state\n    author\n    created\n    plugins\n    network\n    type\n    quorum\n    symbol\n    strategies {\n      name\n      network\n      params\n    }\n    space {\n      id\n      name\n    }\n    scores_state\n    scores\n    scores_by_strategy\n    scores_total\n    votes\n  }\n}"],null),
function(d){return $APP.S(new $APP.J(null,4,5,$APP.K,[BM,$APP.mm(d.data.proposal,$APP.B([$APP.nm,!0])),yM,c],null))});return $APP.F});
$APP.hs(zM,function(a,b){$APP.C(b,0,null);var c=$APP.C(b,1,null);tM(new $APP.h(null,3,[EM,"Votes",CM,new $APP.h(null,5,[$APP.bq,c,HM,"vp",FM,"desc",GM,1,DM,""],null),$APP.LH,"query Votes($id: String!, $first: Int, $skip: Int, $orderBy: String, $orderDirection: OrderDirection, $voter: String) {\n  votes(\n    first: $first\n    skip: $skip\n    where: {proposal: $id, vp_gt: 0, voter: $voter}\n    orderBy: $orderBy\n    orderDirection: $orderDirection\n  ) {\n    ipfs\n    voter\n    choice\n    vp\n    vp_by_strategy\n  }\n}"],null),
function(d){return $APP.S(new $APP.J(null,4,5,$APP.K,[BM,$APP.mm(d.data.votes,$APP.B([$APP.nm,!0])),zM,c],null))});return $APP.F});$APP.$K={};$APP.ms(vM,$APP.B([function(a,b){$APP.C(b,0,null);var c=$APP.C(b,1,null);b=$APP.Nj(a,new $APP.J(null,2,5,$APP.K,[yM,c],null));a=$APP.Nj(a,new $APP.J(null,2,5,$APP.K,[zM,c],null));return new $APP.h(null,2,[wM,b,xM,a],null)}]));$APP.Le();
}).call(this);