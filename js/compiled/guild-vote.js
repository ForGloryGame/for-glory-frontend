(function(){
'use strict';var AL,zL,IL,JL,EL,KL,LL,HL,GL,FL,yL,CL,ML,NL,DL,BL,OL;AL=function(a,b){$APP.Oo($APP.Mo($APP.Mo(fetch("https://hub.snapshot.org/graphql",$APP.uq(new $APP.h(null,4,[$APP.Bu,yL,$APP.EC,zL,$APP.bE,new $APP.h(null,1,["content-type","application/json"],null),$APP.CC,JSON.stringify($APP.uq(a))],null))),function(c){if($APP.k(c.ok)){c=[c,c.json];var d=c[1];return null!=d?d.call(c[0]):null}console.error("Error in response",c);throw Error(c);}),b),console.error)};
$APP.KK=function(){return new $APP.J(null,1,5,$APP.K,[new $APP.h(null,2,[$APP.hr,$APP.ai,$APP.$u,$APP.ai],null)],null)};
$APP.JK=function(a){a=$APP.H(a);a=$APP.E.j(a,$APP.mw);a=$APP.H(a);var b=$APP.E.j(a,BL);a=$APP.p($APP.Ws(new $APP.J(null,2,5,$APP.K,[CL,b],null)));a=$APP.H(a);var c=$APP.E.j(a,DL),d=$APP.E.j(a,EL);return $APP.eq(new $APP.h(null,2,[$APP.oD,function(){$APP.k(b)&&($APP.S(new $APP.J(null,2,5,$APP.K,[FL,b],null)),$APP.S(new $APP.J(null,2,5,$APP.K,[GL,b],null)));return $APP.S(new $APP.J(null,3,5,$APP.K,[$APP.xC,$APP.PF,new $APP.h(null,1,[BL,"0x0eb23ea0b877666ad3ddcd0d7da0114acdfe5ae6390b5628b7509f4338022db5"],
null)],null))},$APP.Aq,function(){return new $APP.J(null,4,5,$APP.K,[$APP.vz,"vote",new $APP.J(null,2,5,$APP.K,[HL,$APP.n.o(c)],null),new $APP.J(null,2,5,$APP.K,[HL,$APP.n.o(d)],null)],null)}],null),$APP.fJ)};zL=$APP.O("cors");IL=$APP.P("fgl.app.snapshot","set");JL=$APP.O("variables");EL=$APP.O("votes");KL=$APP.O("voter");LL=$APP.O("operationName");HL=$APP.O("pre");GL=$APP.P("fgl.app.snapshot","votes");FL=$APP.P("fgl.app.snapshot","proposal");yL=$APP.O("post");
CL=$APP.P("fgl.app.views.guild-vote","data");ML=$APP.O("orderDirection");NL=$APP.O("first");DL=$APP.O("proposal");BL=$APP.O("proposal-id");OL=$APP.O("orderBy");$APP.ed("guild-vote");$APP.mJ.j(IL,function(a,b){b=$APP.q(b);$APP.u(b);var c=$APP.w(b);b=$APP.u(c);c=$APP.w(c);return $APP.$q(a,c,b)});
$APP.Ps(FL,function(a,b){$APP.C(b,0,null);var c=$APP.C(b,1,null);AL(new $APP.h(null,3,[LL,"Proposal",JL,new $APP.h(null,1,[$APP.ho,c],null),$APP.yH,"query Proposal($id: String!) {\n  proposal(id: $id) {\n    id\n    ipfs\n    title\n    body\n    discussion\n    choices\n    start\n    end\n    snapshot\n    state\n    author\n    created\n    plugins\n    network\n    type\n    quorum\n    symbol\n    strategies {\n      name\n      network\n      params\n    }\n    space {\n      id\n      name\n    }\n    scores_state\n    scores\n    scores_by_strategy\n    scores_total\n    votes\n  }\n}"],null),
function(d){return $APP.S(new $APP.J(null,4,5,$APP.K,[IL,$APP.mm(d.data.proposal,$APP.B([$APP.nm,!0])),FL,c],null))});return $APP.F});
$APP.Ps(GL,function(a,b){$APP.C(b,0,null);var c=$APP.C(b,1,null);AL(new $APP.h(null,3,[LL,"Votes",JL,new $APP.h(null,5,[$APP.ho,c,OL,"vp",ML,"desc",NL,1,KL,""],null),$APP.yH,"query Votes($id: String!, $first: Int, $skip: Int, $orderBy: String, $orderDirection: OrderDirection, $voter: String) {\n  votes(\n    first: $first\n    skip: $skip\n    where: {proposal: $id, vp_gt: 0, voter: $voter}\n    orderBy: $orderBy\n    orderDirection: $orderDirection\n  ) {\n    ipfs\n    voter\n    choice\n    vp\n    vp_by_strategy\n  }\n}"],null),
function(d){return $APP.S(new $APP.J(null,4,5,$APP.K,[IL,$APP.mm(d.data.votes,$APP.B([$APP.nm,!0])),GL,c],null))});return $APP.F});$APP.IK={};$APP.Us(CL,$APP.B([function(a,b){$APP.C(b,0,null);var c=$APP.C(b,1,null);b=$APP.Nj(a,new $APP.J(null,2,5,$APP.K,[FL,c],null));a=$APP.Nj(a,new $APP.J(null,2,5,$APP.K,[GL,c],null));return new $APP.h(null,2,[DL,b,EL,a],null)}]));$APP.Le();
}).call(this);