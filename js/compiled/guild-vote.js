(function(){
'use strict';var yM,GM,HM,CM,IM,JM,FM,EM,DM,xM,AM,KM,LM,BM,zM,MM;yM=function(a,b){$APP.cu($APP.au($APP.au(fetch("https://hub.snapshot.org/graphql",$APP.Mp(new $APP.g(null,4,[$APP.tu,xM,$APP.VC,$APP.iz,$APP.oE,new $APP.g(null,1,["content-type","application/json"],null),$APP.TC,JSON.stringify($APP.Mp(a))],null))),function(c){if($APP.h(c.ok)){c=[c,c.json];var d=c[1];return null!=d?d.call(c[0]):null}console.error("Error in response",c);throw Error(c);}),b),console.error)};
$APP.hL=function(){return new $APP.I(null,1,5,$APP.J,[new $APP.g(null,2,[$APP.zq,$APP.Zh,$APP.mv,$APP.Zh],null)],null)};
$APP.gL=function(a){a=$APP.F(a);a=$APP.E.j(a,$APP.zw);a=$APP.F(a);var b=$APP.E.j(a,zM);a=$APP.p($APP.ns(new $APP.I(null,2,5,$APP.J,[AM,b],null)));a=$APP.F(a);var c=$APP.E.j(a,BM),d=$APP.E.j(a,CM);return $APP.wp(new $APP.g(null,2,[$APP.FD,function(){$APP.h(b)&&($APP.Q(new $APP.I(null,2,5,$APP.J,[DM,b],null)),$APP.Q(new $APP.I(null,2,5,$APP.J,[EM,b],null)));return $APP.Q(new $APP.I(null,3,5,$APP.J,[$APP.$u,$APP.eG,new $APP.g(null,1,[zM,"0x0eb23ea0b877666ad3ddcd0d7da0114acdfe5ae6390b5628b7509f4338022db5"],
null)],null))},$APP.Sp,function(){return new $APP.I(null,4,5,$APP.J,[$APP.Lz,"vote",new $APP.I(null,2,5,$APP.J,[FM,$APP.n.o(c)],null),new $APP.I(null,2,5,$APP.J,[FM,$APP.n.o(d)],null)],null)}],null),$APP.zJ)};GM=$APP.O("fgl.app.snapshot","set");HM=$APP.M("variables");CM=$APP.M("votes");IM=$APP.M("voter");JM=$APP.M("operationName");FM=$APP.M("pre");EM=$APP.O("fgl.app.snapshot","votes");DM=$APP.O("fgl.app.snapshot","proposal");xM=$APP.M("post");AM=$APP.O("fgl.app.views.guild-vote","data");KM=$APP.M("orderDirection");
LM=$APP.M("first");BM=$APP.M("proposal");zM=$APP.M("proposal-id");MM=$APP.M("orderBy");$APP.cd("guild-vote");$APP.GJ.j(GM,function(a,b){b=$APP.q(b);$APP.v(b);var c=$APP.w(b);b=$APP.v(c);c=$APP.w(c);return $APP.rq(a,c,b)});
$APP.gs(DM,function(a,b){$APP.D(b,0,null);var c=$APP.D(b,1,null);yM(new $APP.g(null,3,[JM,"Proposal",HM,new $APP.g(null,1,[$APP.aq,c],null),$APP.QH,"query Proposal($id: String!) {\n  proposal(id: $id) {\n    id\n    ipfs\n    title\n    body\n    discussion\n    choices\n    start\n    end\n    snapshot\n    state\n    author\n    created\n    plugins\n    network\n    type\n    quorum\n    symbol\n    strategies {\n      name\n      network\n      params\n    }\n    space {\n      id\n      name\n    }\n    scores_state\n    scores\n    scores_by_strategy\n    scores_total\n    votes\n  }\n}"],null),
function(d){return $APP.Q(new $APP.I(null,4,5,$APP.J,[GM,$APP.lm(d.data.proposal,$APP.B([$APP.mm,!0])),DM,c],null))});return $APP.Ti});
$APP.gs(EM,function(a,b){$APP.D(b,0,null);var c=$APP.D(b,1,null);yM(new $APP.g(null,3,[JM,"Votes",HM,new $APP.g(null,5,[$APP.aq,c,MM,"vp",KM,"desc",LM,1,IM,""],null),$APP.QH,"query Votes($id: String!, $first: Int, $skip: Int, $orderBy: String, $orderDirection: OrderDirection, $voter: String) {\n  votes(\n    first: $first\n    skip: $skip\n    where: {proposal: $id, vp_gt: 0, voter: $voter}\n    orderBy: $orderBy\n    orderDirection: $orderDirection\n  ) {\n    ipfs\n    voter\n    choice\n    vp\n    vp_by_strategy\n  }\n}"],null),
function(d){return $APP.Q(new $APP.I(null,4,5,$APP.J,[GM,$APP.lm(d.data.votes,$APP.B([$APP.mm,!0])),EM,c],null))});return $APP.Ti});$APP.fL={};$APP.ls(AM,$APP.B([function(a,b){$APP.D(b,0,null);var c=$APP.D(b,1,null);b=$APP.Mj(a,new $APP.I(null,2,5,$APP.J,[DM,c],null));a=$APP.Mj(a,new $APP.I(null,2,5,$APP.J,[EM,c],null));return new $APP.g(null,2,[BM,b,CM,a],null)}]));$APP.Je();
}).call(this);