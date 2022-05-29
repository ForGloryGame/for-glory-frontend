(function(){
shadow$provide[419]=function(Q,w,X,g){var E,G,t,u=w(413).useId,b=w(393).Primitive,r=w(352).useComposedRefs;Q=w(414).createContext;var m=(E={},G=w(351),Object.keys(G).forEach(function(h){"default"!==h&&"__esModule"!==h&&Object.defineProperty(E,h,{enumerable:!0,get:function(){return G[h]}})}),E),k=(t=w(353))&&t.__esModule?t.default:t;const [e,d]=Q("Label",{id:void 0,controlRef:{current:null}});w=m.forwardRef((h,v)=>{const {htmlFor:C,id:I,...K}=h,F=m.useRef(null),H=m.useRef(null);v=r(v,H);const L=u(I);
return m.useEffect(()=>{if(C){const N=document.getElementById(C);if(H.current&&N){const O=[L,N.getAttribute("aria-labelledby")].filter(Boolean).join(" ");return N.setAttribute("aria-labelledby",O),F.current=N,()=>{var M;const U=null===(M=N.getAttribute("aria-labelledby"))||void 0===M?void 0:M.replace(L,"");""===U?N.removeAttribute("aria-labelledby"):U&&N.setAttribute("aria-labelledby",U)}}}},[L,C]),m.createElement(e,{id:L,controlRef:F},m.createElement(b.span,k({role:"label",id:L},K,{ref:v,onMouseDown:N=>
{var O;null===(O=h.onMouseDown)||void 0===O||O.call(h,N);!N.defaultPrevented&&1<N.detail&&N.preventDefault()},onClick:N=>{var O;if(null===(O=h.onClick)||void 0===O||O.call(h,N),F.current&&!N.defaultPrevented)O=F.current.contains(N.target),N=!0===N.isTrusted,!O&&N&&(F.current.click(),F.current.focus())}})))});g.Label=w;g.useLabelContext=h=>{const v=d("LabelConsumer"),{controlRef:C}=v;return m.useEffect(()=>{h&&(C.current=h)},[h,C]),v.id};g.Root=w};
shadow$provide[420]=function(Q,w,X,g){g.clamp=function(E,[G,t]){return Math.min(t,Math.max(G,E))}};
shadow$provide[421]=function(Q,w,X,g){function E(na,pa){return Object.keys(pa).forEach(function(qa){"default"!==qa&&"__esModule"!==qa&&Object.defineProperty(na,qa,{enumerable:!0,get:function(){return pa[qa]}})}),na}function G(na){const pa=h(na),qa=U.useRef(""),ua=U.useRef(0);na=U.useCallback(za=>{za=qa.current+za;pa(za);(function Ea(Ba){qa.current=Ba;window.clearTimeout(ua.current);""!==Ba&&(ua.current=window.setTimeout(()=>Ea(""),1E3))})(za)},[pa]);const xa=U.useCallback(()=>{qa.current="";window.clearTimeout(ua.current)},
[]);return U.useEffect(()=>()=>window.clearTimeout(ua.current),[]),[qa,na,xa]}function t(na,pa,qa){const ua=1<pa.length&&Array.from(pa).every(Ba=>Ba===pa[0])?pa[0]:pa;var xa=qa?na.indexOf(qa):-1;na=(za=na,Aa=Math.max(xa,0),za.map((Ba,Ea)=>za[(Aa+Ea)%za.length]));var za,Aa;1===ua.length&&(na=na.filter(Ba=>Ba!==qa));xa=na.find(Ba=>Ba.textValue.toLowerCase().startsWith(ua.toLowerCase()));return xa!==qa?xa:void 0}var u,b=w(391).RemoveScroll,r=w(355).hideOthers,m=w(416).VisuallyHidden,k=w(417).usePrevious,
e=w(394).useLayoutEffect,d=w(412).useControllableState,h=w(406).useCallbackRef,v=w(393).Primitive,C=w(405).Portal,I=w(419).useLabelContext,K=w(413).useId,F=w(407).FocusScope,H=w(411).DismissableLayer;Q=w(414).createContextScope;var L=w(352).useComposedRefs;X=w(418).createCollection;var N=w(410).composeEventHandlers,O=w(420).clamp,M=E({},w(404)),U=E({},w(351)),P=(u=w(353))&&u.__esModule?u.default:u;const c=[" ","Enter","ArrowUp","ArrowDown"],z=[" ","Enter"],[a,f,l]=X("Select"),[n,q]=Q("Select",[l]);
g.createSelectScope=q;const [B,x]=n("Select");w=na=>{const {__scopeSelect:pa,children:qa,open:ua,defaultOpen:xa,onOpenChange:za,value:Aa,defaultValue:Ba,onValueChange:Ea,dir:Ga,name:Ka,autoComplete:Fa}=na,[La,Ia]=U.useState(null),[Qa,Na]=U.useState(null),[Ja,ja]=U.useState(!1),[Ra=!1,ea]=d({prop:ua,defaultProp:xa,onChange:za}),[eb="",fb]=d({prop:Aa,defaultProp:Ba,onChange:Ea});na=!La||!!La.closest("form");const [jb,rb]=U.useState(null),hb=U.useRef(null);return U.createElement(B,{scope:pa,trigger:La,
onTriggerChange:Ia,valueNode:Qa,onValueNodeChange:Na,valueNodeHasChildren:Ja,onValueNodeHasChildrenChange:ja,contentId:K(),value:eb,onValueChange:fb,open:Ra,onOpenChange:ea,dir:Ga,bubbleSelect:jb,triggerPointerDownPosRef:hb},U.createElement(a.Provider,{scope:pa},qa),na?U.createElement(ra,{ref:rb,"aria-hidden":!0,tabIndex:-1,name:Ka,autoComplete:Fa,value:eb,onChange:nb=>fb(nb.target.value)}):null)};g.Select=w;u=U.forwardRef((na,pa)=>{const {__scopeSelect:qa,disabled:ua=!1,"aria-labelledby":xa,...za}=
na,Aa=x("SelectTrigger",qa);na=L(pa,Aa.onTriggerChange);const Ba=f(qa);pa=I(Aa.trigger);pa=xa||pa;const [Ea,Ga,Ka]=G(Fa=>{const La=Ba().filter(Qa=>!Qa.disabled),Ia=La.find(Qa=>Qa.value===Aa.value);Fa=t(La,Fa,Ia);void 0!==Fa&&Aa.onValueChange(Fa.value)});return U.createElement(v.button,P({type:"button",role:"combobox","aria-controls":Aa.contentId,"aria-expanded":Aa.open,"aria-autocomplete":"none","aria-labelledby":pa,dir:Aa.dir,disabled:ua,"data-disabled":ua?"":void 0},za,{ref:na,onPointerDown:N(za.onPointerDown,
Fa=>{Fa.target.releasePointerCapture(Fa.pointerId);0===Fa.button&&!1===Fa.ctrlKey&&(ua||(Aa.onOpenChange(!0),Ka()),Aa.triggerPointerDownPosRef.current={x:Math.round(Fa.pageX),y:Math.round(Fa.pageY)},Fa.preventDefault())}),onKeyDown:N(za.onKeyDown,Fa=>{const La=""!==Ea.current;Fa.ctrlKey||Fa.altKey||Fa.metaKey||1!==Fa.key.length||Ga(Fa.key);La&&" "===Fa.key||!c.includes(Fa.key)||(ua||(Aa.onOpenChange(!0),Ka()),Fa.preventDefault())})}))});g.SelectTrigger=u;Q=U.forwardRef((na,pa)=>{const {__scopeSelect:qa,
className:ua,style:xa,...za}=na,Aa=x("SelectValue",qa),{onValueNodeHasChildrenChange:Ba}=Aa,Ea=void 0!==na.children;na=L(pa,Aa.onValueNodeChange);return U.useEffect(()=>{Ba(Ea)},[Ba,Ea]),U.createElement(v.span,P({},za,{ref:na,style:{pointerEvents:"none"}}))});g.SelectValue=Q;X=U.forwardRef((na,pa)=>{const {__scopeSelect:qa,children:ua,...xa}=na;return U.createElement(v.span,P({"aria-hidden":!0},xa,{ref:pa}),ua||"▼")});g.SelectIcon=X;const D=U.forwardRef((na,pa)=>{const qa=x("SelectContent",na.__scopeSelect),
[ua,xa]=U.useState();return e(()=>{xa(new DocumentFragment)},[]),qa.open?U.createElement(T,P({},na,{ref:pa})):ua?M.createPortal(U.createElement(A,{scope:na.__scopeSelect},U.createElement(a.Slot,{scope:na.__scopeSelect},U.createElement("div",null,na.children))),ua):null});g.SelectContent=D;const [A,R]=n("SelectContent"),T=U.forwardRef((na,pa)=>{const {__scopeSelect:qa,onCloseAutoFocus:ua,children:xa,...za}=na,Aa=x("SelectContent",qa),[Ba,Ea]=U.useState(null),[Ga,Ka]=U.useState(null),[Fa,La]=U.useState(null);
na=L(pa,Wa=>Ka(Wa));const [Ia,Qa]=U.useState(null),[Na,Ja]=U.useState(null),ja=f(qa),[Ra,ea]=U.useState(!1),eb=U.useRef(!0),fb=U.useRef(!1);U.useEffect(()=>{if(Ga)return r(Ga)},[Ga]);const jb=U.useCallback(Wa=>{const [gb,...kb]=ja().map(tb=>tb.ref.current),[ib]=kb.slice(-1),wb=document.activeElement;for(const tb of Wa){if(tb===wb)break;if(null==tb||tb.scrollIntoView({block:"nearest"}),tb===gb&&Fa&&(Fa.scrollTop=0),tb===ib&&Fa&&(Fa.scrollTop=Fa.scrollHeight),null==tb||tb.focus(),document.activeElement!==
wb)break}},[ja,Fa]),rb=U.useCallback(()=>{if(Aa.trigger&&Aa.valueNode&&Ba&&Ga&&Fa&&Ia&&Na){var Wa=Aa.trigger.getBoundingClientRect(),gb=Ga.getBoundingClientRect(),kb=Aa.valueNode.getBoundingClientRect(),ib=Na.getBoundingClientRect();"rtl"!==Aa.dir?(ib=kb.left-(ib.left-gb.left),kb=Wa.width+(Wa.left-ib),gb=O(ib,[10,window.innerWidth-10-Math.max(kb,gb.width)]),Ba.style.minWidth=kb+"px",Ba.style.left=gb+"px"):(ib=window.innerWidth-kb.right-(gb.right-ib.right),kb=Wa.width+(window.innerWidth-Wa.right-ib),
gb=O(ib,[10,window.innerWidth-10-Math.max(kb,gb.width)]),Ba.style.minWidth=kb+"px",Ba.style.right=gb+"px");gb=window.innerHeight-20;kb=5*Ia.offsetHeight;var wb=Fa.scrollHeight,tb=window.getComputedStyle(Ga);ib=parseInt(tb.borderTopWidth,10);var Jb=parseInt(tb.paddingTop,10);const Xb=parseInt(tb.borderBottomWidth,10);wb=ib+Jb+wb+parseInt(tb.paddingBottom,10)+Xb;Wa=Wa.top+Wa.height/2-10;tb=gb-Wa;Jb=Ia.offsetHeight/2;const Yb=ib+(Ia.offsetTop+Jb);wb-=Yb;Yb<=Wa?(Ba.style.bottom="0px",Ba.style.height=
Yb+Math.max(tb,Jb+(Ga.clientHeight-Fa.offsetTop-Fa.offsetHeight)+Xb)+"px"):(Ba.style.top="0px",Ba.style.height=Math.max(Wa,ib+Fa.offsetTop+Jb)+wb+"px",Fa.scrollTop=Yb-Wa+Fa.offsetTop);Ba.style.margin="10px 0";Ba.style.minHeight=kb+"px";Ba.style.maxHeight=gb+"px";ea(!0);requestAnimationFrame(()=>fb.current=!0)}},[Aa.trigger,Aa.valueNode,Ba,Ga,Fa,Ia,Na,Aa.dir]);e(()=>rb(),[rb]);const hb=U.useCallback(()=>jb([Ia,Ga]),[jb,Ia,Ga]);U.useEffect(()=>{Ra&&hb()},[Ra,hb]);pa=U.useCallback(Wa=>{Wa&&!0===eb.current&&
(rb(),hb(),eb.current=!1)},[rb,hb]);const {onOpenChange:nb,triggerPointerDownPosRef:lb}=Aa;U.useEffect(()=>{if(Ga){var Wa=0,gb=0;const kb=wb=>{var tb,Jb,Xb,Yb;Wa=Math.abs(Math.round(wb.pageX)-(null!==(tb=null===(Jb=lb.current)||void 0===Jb?void 0:Jb.x)&&void 0!==tb?tb:0));gb=Math.abs(Math.round(wb.pageY)-(null!==(Xb=null===(Yb=lb.current)||void 0===Yb?void 0:Yb.y)&&void 0!==Xb?Xb:0))},ib=wb=>{10>=Wa&&10>=gb?wb.preventDefault():Ga.contains(wb.target)||nb(!1);document.removeEventListener("pointermove",
kb);lb.current=null};return null!==lb.current&&(document.addEventListener("pointermove",kb),document.addEventListener("pointerup",ib,{capture:!0,once:!0})),()=>{document.removeEventListener("pointermove",kb);document.removeEventListener("pointerup",ib,{capture:!0})}}},[Ga,nb,lb]);U.useEffect(()=>{const Wa=()=>nb(!1);return window.addEventListener("blur",Wa),window.addEventListener("resize",Wa),()=>{window.removeEventListener("blur",Wa);window.removeEventListener("resize",Wa)}},[nb]);const [xb,Ob]=
G(Wa=>{const gb=ja().filter(wb=>!wb.disabled),kb=gb.find(wb=>wb.ref.current===document.activeElement),ib=t(gb,Wa,kb);ib&&setTimeout(()=>ib.ref.current.focus())}),Vc=U.useCallback(()=>null==Ga?void 0:Ga.focus(),[Ga]);return U.createElement(C,null,U.createElement(b,null,U.createElement("div",{ref:Ea,style:{display:"flex",flexDirection:"column",position:"fixed",zIndex:0}},U.createElement(F,{asChild:!0,trapped:Aa.open,onMountAutoFocus:Wa=>{Wa.preventDefault()},onUnmountAutoFocus:N(ua,Wa=>{var gb;null===
(gb=Aa.trigger)||void 0===gb||gb.focus({preventScroll:!0});Wa.preventDefault()})},U.createElement(H,P({role:"listbox",id:Aa.contentId,"data-state":Aa.open?"open":"closed",dir:Aa.dir,onContextMenu:Wa=>Wa.preventDefault()},za,{ref:na,style:{display:"flex",flexDirection:"column",boxSizing:"border-box",maxHeight:"100%",outline:"none",...za.style},disableOutsidePointerEvents:!0,onFocusOutside:Wa=>Wa.preventDefault(),onDismiss:()=>Aa.onOpenChange(!1),onKeyDown:N(za.onKeyDown,Wa=>{var gb=Wa.ctrlKey||Wa.altKey||
Wa.metaKey;if("Tab"===Wa.key&&Wa.preventDefault(),gb||1!==Wa.key.length||Ob(Wa.key),["ArrowUp","ArrowDown","Home","End"].includes(Wa.key)){let kb=ja().filter(ib=>!ib.disabled).map(ib=>ib.ref.current);if(["ArrowUp","End"].includes(Wa.key)&&(kb=kb.slice().reverse()),["ArrowUp","ArrowDown"].includes(Wa.key))gb=kb.indexOf(Wa.target),kb=kb.slice(gb+1);setTimeout(()=>jb(kb));Wa.preventDefault()}})}),U.createElement(A,{scope:qa,contentWrapper:Ba,content:Ga,viewport:Fa,onViewportChange:La,selectedItem:Ia,
onSelectedItemChange:Qa,selectedItemText:Na,onSelectedItemTextChange:Ja,onScrollButtonChange:pa,onItemLeave:Vc,isPositioned:Ra,shouldExpandOnScrollRef:fb,searchRef:xb},xa))))))}),S=U.forwardRef((na,pa)=>{const {__scopeSelect:qa,...ua}=na,xa=R("SelectViewport",qa);na=L(pa,xa.onViewportChange);const za=U.useRef(0);return U.createElement(U.Fragment,null,U.createElement("style",{dangerouslySetInnerHTML:{__html:"[data-radix-select-viewport]{scrollbar-width:none;-ms-overflow-style:none;-webkit-overflow-scrolling:touch;}[data-radix-select-viewport]::-webkit-scrollbar{display:none}"}}),
U.createElement(a.Slot,{scope:qa},U.createElement(v.div,P({"data-radix-select-viewport":"",role:"presentation"},ua,{ref:na,style:{position:"relative",flex:1,overflow:"auto",...ua.style},onScroll:N(ua.onScroll,Aa=>{Aa=Aa.currentTarget;const {contentWrapper:Ba,shouldExpandOnScrollRef:Ea}=xa;if(Ea.current&&Ba){var Ga=Math.abs(za.current-Aa.scrollTop);if(0<Ga){var Ka=window.innerHeight-20,Fa=parseFloat(Ba.style.minHeight);const La=parseFloat(Ba.style.height);Fa=Math.max(Fa,La);Fa<Ka&&(Ga=Fa+Ga,Ka=Math.min(Ka,
Ga),Ga-=Ka,Ba.style.height=Ka+"px","0px"===Ba.style.bottom&&(Aa.scrollTop=0<Ga?Ga:0,Ba.style.justifyContent="flex-end"))}}za.current=Aa.scrollTop})}))))});g.SelectViewport=S;const [V,aa]=n("SelectGroup"),ba=U.forwardRef((na,pa)=>{const {__scopeSelect:qa,...ua}=na;na=K();return U.createElement(V,{scope:qa,id:na},U.createElement(v.div,P({role:"group","aria-labelledby":na},ua,{ref:pa})))});g.SelectGroup=ba;const ca=U.forwardRef((na,pa)=>{const {__scopeSelect:qa,...ua}=na;na=aa("SelectLabel",qa);return U.createElement(v.div,
P({id:na.id},ua,{ref:pa}))});g.SelectLabel=ca;const [Y,fa]=n("SelectItem"),ha=U.forwardRef((na,pa)=>{const {__scopeSelect:qa,value:ua,disabled:xa=!1,textValue:za,...Aa}=na,Ba=x("SelectItem",qa),Ea=R("SelectItem",qa);na=Ba.value===ua;const [Ga,Ka]=U.useState(null!=za?za:""),[Fa,La]=U.useState(!1);pa=L(pa,na?Ea.onSelectedItemChange:void 0);const Ia=K(),Qa=()=>{xa||(Ba.onValueChange(ua),Ba.onOpenChange(!1))};return U.createElement(Y,{scope:qa,value:ua,textId:Ia,isSelected:na,onItemTextChange:U.useCallback(Na=>
{Ka(Ja=>{var ja;return Ja||(null!==(ja=null==Na?void 0:Na.textContent)&&void 0!==ja?ja:"").trim()})},[])},U.createElement(a.ItemSlot,{scope:qa,value:ua,disabled:xa,textValue:Ga},U.createElement(v.div,P({role:"option","aria-labelledby":Ia,"aria-selected":na&&Fa,"data-state":na?"active":"inactive","aria-disabled":xa||void 0,"data-disabled":xa?"":void 0,tabIndex:xa?void 0:-1},Aa,{ref:pa,onFocus:N(Aa.onFocus,()=>La(!0)),onBlur:N(Aa.onBlur,()=>La(!1)),onPointerUp:N(Aa.onPointerUp,Qa),onPointerMove:N(Aa.onPointerMove,
Na=>{xa?Ea.onItemLeave():Na.currentTarget.focus({preventScroll:!0})}),onPointerLeave:N(Aa.onPointerLeave,Na=>{Na.currentTarget===document.activeElement&&Ea.onItemLeave()}),onKeyDown:N(Aa.onKeyDown,Na=>{""!==Ea.searchRef.current&&" "===Na.key||(z.includes(Na.key)&&Qa()," "===Na.key&&Na.preventDefault())})}))))});g.SelectItem=ha;const ka=U.forwardRef((na,pa)=>{var qa;const {__scopeSelect:ua,className:xa,style:za,...Aa}=na;na=x("SelectItemText",ua);const Ba=R("SelectItemText",ua),Ea=fa("SelectItemText",
ua),Ga=U.useRef(null);pa=L(pa,Ga,Ea.onItemTextChange,Ea.isSelected?Ba.onSelectedItemTextChange:void 0);return U.createElement(U.Fragment,null,U.createElement(v.span,P({id:Ea.textId},Aa,{ref:pa})),Ea.isSelected&&na.valueNode&&!na.valueNodeHasChildren?M.createPortal(Aa.children,na.valueNode):null,na.bubbleSelect?M.createPortal(U.createElement("option",{value:Ea.value},null===(qa=Ga.current)||void 0===qa?void 0:qa.textContent),na.bubbleSelect):null)});g.SelectItemText=ka;const oa=U.forwardRef((na,pa)=>
{const {__scopeSelect:qa,...ua}=na;return fa("SelectItemIndicator",qa).isSelected?U.createElement(v.span,P({"aria-hidden":!0},ua,{ref:pa})):null});g.SelectItemIndicator=oa;const la=U.forwardRef((na,pa)=>{const qa=R("SelectScrollUpButton",na.__scopeSelect),[ua,xa]=U.useState(!1);pa=L(pa,qa.onScrollButtonChange);return U.useEffect(()=>{if(qa.viewport&&qa.isPositioned){const za=qa.viewport;function Aa(){xa(0<za.scrollTop)}return Aa(),za.addEventListener("scroll",Aa),()=>za.removeEventListener("scroll",
Aa)}},[qa.viewport,qa.isPositioned]),ua?U.createElement(ta,P({},na,{ref:pa,onAutoScroll:()=>{const {viewport:za,selectedItem:Aa}=qa;za&&Aa&&(za.scrollTop-=Aa.offsetHeight)}})):null});g.SelectScrollUpButton=la;const va=U.forwardRef((na,pa)=>{const qa=R("SelectScrollDownButton",na.__scopeSelect),[ua,xa]=U.useState(!1);pa=L(pa,qa.onScrollButtonChange);return U.useEffect(()=>{if(qa.viewport&&qa.isPositioned){const za=qa.viewport;function Aa(){xa(Math.ceil(za.scrollTop)<za.scrollHeight-za.clientHeight)}
return Aa(),za.addEventListener("scroll",Aa),()=>za.removeEventListener("scroll",Aa)}},[qa.viewport,qa.isPositioned]),ua?U.createElement(ta,P({},na,{ref:pa,onAutoScroll:()=>{const {viewport:za,selectedItem:Aa}=qa;za&&Aa&&(za.scrollTop+=Aa.offsetHeight)}})):null});g.SelectScrollDownButton=va;const ta=U.forwardRef((na,pa)=>{const {__scopeSelect:qa,onAutoScroll:ua,...xa}=na,za=R("SelectScrollButton",qa),Aa=U.useRef(null),Ba=f(qa),Ea=U.useCallback(()=>{null!==Aa.current&&(window.clearInterval(Aa.current),
Aa.current=null)},[]);return U.useEffect(()=>()=>Ea(),[Ea]),e(()=>{var Ga;const Ka=Ba().find(Fa=>Fa.ref.current===document.activeElement);null==Ka||null===(Ga=Ka.ref.current)||void 0===Ga||Ga.scrollIntoView({block:"nearest"})},[Ba]),U.createElement(v.div,P({"aria-hidden":!0},xa,{ref:pa,style:{flexShrink:0,...xa.style},onPointerMove:N(xa.onPointerMove,()=>{za.onItemLeave();null===Aa.current&&(Aa.current=window.setInterval(ua,50))}),onPointerLeave:N(xa.onPointerLeave,()=>{Ea()})}))}),sa=U.forwardRef((na,
pa)=>{const {__scopeSelect:qa,...ua}=na;return U.createElement(v.div,P({"aria-hidden":!0},ua,{ref:pa}))});g.SelectSeparator=sa;const ra=U.forwardRef((na,pa)=>{const {value:qa,...ua}=na,xa=U.useRef(null);na=L(pa,xa);const za=k(qa);return U.useEffect(()=>{const Aa=xa.current,Ba=Object.getOwnPropertyDescriptor(window.HTMLSelectElement.prototype,"value").set;if(za!==qa&&Ba){const Ea=new Event("change",{bubbles:!0});Ba.call(Aa,qa);Aa.dispatchEvent(Ea)}},[za,qa]),U.createElement(m,{asChild:!0},U.createElement("select",
P({},ua,{ref:na,defaultValue:qa})))});g.Root=w;g.Trigger=u;g.Value=Q;g.Icon=X;g.Content=D;g.Viewport=S;g.Group=ba;g.Label=ca;g.Item=ha;g.ItemText=ka;g.ItemIndicator=oa;g.ScrollUpButton=la;g.ScrollDownButton=va;g.Separator=sa};
shadow$provide[422]=function(Q,w,X,g){var E,G,t=(E={},G=w(351),Object.keys(G).forEach(function(u){"default"!==u&&"__esModule"!==u&&Object.defineProperty(E,u,{enumerable:!0,get:function(){return G[u]}})}),E);g.useSize=function(u){const [b,r]=t.useState(void 0);return t.useEffect(()=>{if(u){const m=new ResizeObserver(k=>{if(Array.isArray(k)&&k.length){k=k[0];if("borderBoxSize"in k){k=k.borderBoxSize;var e=Array.isArray(k)?k[0]:k;k=e.inlineSize;e=e.blockSize}else e=u.getBoundingClientRect(),k=e.width,
e=e.height;r({width:k,height:e})}});return m.observe(u,{box:"border-box"}),()=>m.unobserve(u)}r(void 0)},[u]),b}};
shadow$provide[423]=function(Q,w,X,g){function E(M){return"indeterminate"===M}function G(M){return E(M)?"indeterminate":M?"checked":"unchecked"}var t,u,b,r=w(393).Primitive,m=w(395).Presence,k=w(419).useLabelContext,e=w(422).useSize,d=w(417).usePrevious,h=w(412).useControllableState,v=w(410).composeEventHandlers;Q=w(414).createContextScope;var C=w(352).useComposedRefs,I=(t={},u=w(351),Object.keys(u).forEach(function(M){"default"!==M&&"__esModule"!==M&&Object.defineProperty(t,M,{enumerable:!0,get:function(){return u[M]}})}),
t),K=(b=w(353))&&b.__esModule?b.default:b;const [F,H]=Q("Checkbox");g.createCheckboxScope=H;const [L,N]=F("Checkbox");w=I.forwardRef((M,U)=>{const {__scopeCheckbox:P,"aria-labelledby":c,name:z,checked:a,defaultChecked:f,required:l,disabled:n,value:q="on",onCheckedChange:B,...x}=M,[D,A]=I.useState(null);U=C(U,ba=>A(ba));var R=k(D);R=c||R;const T=I.useRef(!1),S=!D||!!D.closest("form"),[V=!1,aa]=h({prop:a,defaultProp:f,onChange:B});return I.createElement(L,{scope:P,state:V,disabled:n},I.createElement(r.button,
K({type:"button",role:"checkbox","aria-checked":E(V)?"mixed":V,"aria-labelledby":R,"aria-required":l,"data-state":G(V),"data-disabled":n?"":void 0,disabled:n,value:q},x,{ref:U,onKeyDown:v(M.onKeyDown,ba=>{"Enter"===ba.key&&ba.preventDefault()}),onClick:v(M.onClick,ba=>{aa(ca=>!!E(ca)||!ca);S&&(T.current=ba.isPropagationStopped(),T.current||ba.stopPropagation())})})),S&&I.createElement(O,{control:D,bubbles:!T.current,name:z,value:q,checked:V,required:l,disabled:n,style:{transform:"translateX(-100%)"}}))});
g.Checkbox=w;b=I.forwardRef((M,U)=>{const {__scopeCheckbox:P,forceMount:c,...z}=M,a=N("CheckboxIndicator",P);return I.createElement(m,{present:c||E(a.state)||!0===a.state},I.createElement(r.span,K({"data-state":G(a.state),"data-disabled":a.disabled?"":void 0},z,{ref:U,style:{pointerEvents:"none",...M.style}})))});g.CheckboxIndicator=b;const O=M=>{const {control:U,checked:P,bubbles:c=!0,...z}=M,a=I.useRef(null),f=d(P),l=e(U);return I.useEffect(()=>{const n=a.current,q=Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype,
"checked").set;if(f!==P&&q){const B=new Event("click",{bubbles:c});n.indeterminate=E(P);q.call(n,!E(P)&&P);n.dispatchEvent(B)}},[f,P,c]),I.createElement("input",K({type:"checkbox","aria-hidden":!0,defaultChecked:!E(P)&&P},z,{tabIndex:-1,ref:a,style:{...M.style,...l,position:"absolute",pointerEvents:"none",opacity:0,margin:0}}))};g.Root=w;g.Indicator=b};
'use strict';var TO,UO,WO,YO,hP,sP,wP,BP,FP,xP,dP,ZO,IP,JP,KP,qP,LP,MP,pP,AP,NP,OP,HP,PP,vP,QP,RP,SP,fP,aP,VO,TP,kP,oP,EP,UP,jP,VP,GP,rP,bP,tP,WP,XP,uP,XO,YP,ZP,eP,cP,gP,lP,mP,$P,yP,CP,nP,aQ,bQ,cQ,DP;TO=function(a){return a|0};UO=function(a){return function e(c,d){return new $APP.xi(null,function(){var f;a:{var l=c;for(f=d;;){var m=l;l=$APP.C(m,0,null);if(m=$APP.q(m))if($APP.Oh(f,l))l=$APP.Sg(m);else{f=$APP.qi(l,e($APP.Sg(m),$APP.ni.j(f,l)));break a}else{f=null;break a}}}return f},null,null)}(a,$APP.vl)};
$APP.LK=function(){return new $APP.J(null,1,5,$APP.K,[new $APP.h(null,2,[$APP.hr,function(){$APP.S(new $APP.J(null,1,5,$APP.K,[$APP.KB],null));$APP.S(new $APP.J(null,1,5,$APP.K,[$APP.dB],null));return $APP.S(new $APP.J(null,1,5,$APP.K,[$APP.SC],null))},$APP.$u,$APP.ai],null)],null)};WO=function(a){return $APP.S(new $APP.J(null,2,5,$APP.K,[$APP.LC,new $APP.h(null,2,[$APP.Bu,VO,$APP.Cu,new $APP.J(null,1,5,$APP.K,[a],null)],null)],null))};
YO=function(){var a=$APP.p($APP.Ws(new $APP.J(null,1,5,$APP.K,[XO],null))),b=$APP.H(a);a=$APP.E.j(b,$APP.hB);b=$APP.E.j(b,$APP.dI);return $APP.k(b)?$APP.S(new $APP.J(null,9,5,$APP.K,[$APP.Vt,$APP.eu,!0,$APP.mu,"Reveal Unstake Commits",$APP.gu,new $APP.J(null,4,5,$APP.K,[$APP.$t,new $APP.J(null,2,5,$APP.K,[$APP.nu,"Found pending commits"],null),new $APP.J(null,1,5,$APP.K,[$APP.bu],null),new $APP.J(null,2,5,$APP.K,[$APP.nu,"Click the REVEAL button below to reveal them"],null)],null),$APP.hu,new $APP.J(null,
3,5,$APP.K,[$APP.Ft,new $APP.h(null,2,[$APP.At,$APP.iu,$APP.Ut,$APP.at.j(WO,a)],null),"REVEAL"],null)],null)):null};
hP=function(){function a(b){return $APP.S(new $APP.J(null,2,5,$APP.K,[ZO,$APP.Tl.o(b)],null))}return function(){var b=$APP.p($APP.Ws(new $APP.J(null,1,5,$APP.K,[XO],null)));b=$APP.H(b);b=$APP.E.j(b,$APP.T);return new $APP.J(null,5,5,$APP.K,[$APP.Rt,$O.Root,new $APP.h(null,4,[$APP.R,"Token Type",aP,"Staked",$APP.fw,b,bP,a],null),new $APP.J(null,5,5,$APP.K,[$APP.Rt,$O.Trigger,new $APP.h(null,2,[$APP.Jq,"text-xl text-center pl-14 pr-10 py-0.5 rounded fb",$APP.qt,new $APP.h(null,1,[$APP.wt,"#355661"],
null)],null),new $APP.J(null,3,5,$APP.K,[$APP.Rt,$O.Value,$APP.z.j(b,cP)?" Staked ":"Unstaked"],null),new $APP.J(null,3,5,$APP.K,[$APP.Rt,$O.Icon,new $APP.J(null,2,5,$APP.K,[dP,new $APP.h(null,2,[$APP.qt,new $APP.h(null,1,[$APP.vt,"0.825rem"],null),$APP.Wu,"/images/select-down-arrow.svg"],null)],null)],null)],null),new $APP.J(null,4,5,$APP.K,[$APP.Rt,$O.Content,new $APP.h(null,2,[$APP.Jq,"text-xl text-center rounded fb pl-14 pr-10",$APP.qt,new $APP.h(null,1,[$APP.wt,"#355661"],null)],null),new $APP.J(null,
4,5,$APP.K,[$APP.Rt,$O.Viewport,$APP.wh(new $APP.J(null,6,5,$APP.K,[$APP.Rt,$O.Item,new $APP.h(null,1,[$APP.fw,cP],null),new $APP.J(null,3,5,$APP.K,[$APP.Rt,$O.ItemText," Staked "],null),new $APP.J(null,3,5,$APP.K,[$APP.Rt,$O.ItemIndicator,null==b||$APP.z.j(b,cP)],null),new $APP.J(null,2,5,$APP.K,[dP,new $APP.h(null,2,[$APP.qt,new $APP.h(null,2,[$APP.vt,"0.825rem",$APP.ID,null==b||$APP.z.j(b,cP)?"visible":"hidden"],null),$APP.Wu,"/images/select-down-arrow.svg"],null)],null)],null),new $APP.h(null,
1,[$APP.Uo,eP],null)),$APP.wh(new $APP.J(null,5,5,$APP.K,[$APP.Rt,$O.Item,new $APP.h(null,1,[$APP.fw,fP],null),new $APP.J(null,3,5,$APP.K,[$APP.Rt,$O.ItemText,"Unstaked"],null),new $APP.J(null,2,5,$APP.K,[dP,new $APP.h(null,2,[$APP.qt,new $APP.h(null,2,[$APP.vt,"0.825rem",$APP.ID,$APP.z.j(b,fP)?"visible":"hidden"],null),$APP.Wu,"/images/select-down-arrow.svg"],null)],null)],null),new $APP.h(null,1,[$APP.Uo,gP],null))],null)],null)],null)}};
sP=function(a,b,c,d,e,f,l){var m=[$APP.k(b)?"Lord#":"Knight#",$APP.n.o(a)].join("");return new $APP.J(null,3,5,$APP.K,[iP,$APP.jl([cP,jP,kP,$APP.R,$APP.vt,$APP.dz,$APP.CA,$APP.ho,lP,mP,nP,oP],[e,f,l,m,"16rem",c,$APP.k(b)?d:b,a,f,function(r){return $APP.S(new $APP.J(null,2,5,$APP.K,[$APP.k(r)?pP:qP,a],null))},$APP.k(b)?"/images/lord-example.png":"/images/knight-example.png",$APP.k(c)?$APP.Ze(b):c]),new $APP.h(null,2,[$APP.Jq,"mr-4",$APP.qt,new $APP.h(null,1,[rP,0],null)],null)],null)};
wP=function(a){function b(d){var e=$APP.p(c);if($APP.k(e)){d.preventDefault();var f=d.deltaY;return $APP.k(f)?e.scrollLeft+=d.deltaY:f}return null}var c=$APP.Lp.o(null);return $APP.eq(new $APP.h(null,3,[$APP.rD,function(){$APP.S(new $APP.J(null,1,5,$APP.K,[pP],null));var d=$APP.p(c);return $APP.k(d)?d.addEventListener("wheel",b,{passive:!1}):null},$APP.Hz,function(){var d=$APP.p(c);return $APP.k(d)?d.removeEventListener("wheel",b):null},$APP.Aq,function(){var d=$APP.p($APP.Ws(new $APP.J(null,2,5,
$APP.K,[XO,a],null))),e=$APP.H(d);d=$APP.E.j(e,$APP.T);e=$APP.E.j(e,$APP.dv);var f=$APP.z.j(d,cP),l=$APP.p($APP.Ws(new $APP.J(null,1,5,$APP.K,[tP],null)));return $APP.fj.j(new $APP.J(null,2,5,$APP.K,[uP,new $APP.h(null,1,[vP,function(m){return $APP.pj(c,m)}],null)],null),$APP.vj.j(function(m){var r=$APP.H(m);m=$APP.E.j(r,$APP.ho);var t=$APP.E.j(r,$APP.VF),w=$APP.E.j(r,$APP.dz);r=$APP.E.j(r,$APP.CA);return $APP.wh(new $APP.J(null,8,5,$APP.K,[sP,m,t,w,r,f,null!=$APP.dj($APP.wl([m]),l),!1],null),new $APP.h(null,
1,[$APP.Uo,m],null))},e))}],null),$APP.pJ)};
BP=function(a){function b(c){return $APP.S(new $APP.J(null,3,5,$APP.K,[xP,c,a],null))}return function(){var c=$APP.p($APP.Ws(new $APP.J(null,2,5,$APP.K,[XO,a],null)));c=$APP.H(c);c=$APP.E.j(c,yP);return new $APP.J(null,3,5,$APP.K,[zP,new $APP.h(null,2,[$APP.vt,"1.5rem",$APP.TF,new $APP.J(null,3,5,$APP.K,[AP,new $APP.h(null,1,[$APP.qt,new $APP.h(null,1,[$APP.DB,"rgb(213, 228, 232)"],null)],null),"SELECT ALL"],null)],null),new $APP.h(null,3,[$APP.Jq,"flex flex-row cs1 ce2 rs1 re2 justify-self-start items-center",mP,
b,lP,c],null)],null)}};
FP=function(){function a(e){return function(){return $APP.S(new $APP.J(null,2,5,$APP.K,[$APP.LC,new $APP.h(null,4,[$APP.Bu,$APP.lz,$APP.Cu,new $APP.J(null,1,5,$APP.K,[$APP.uq($APP.Th($APP.Ph,$APP.vj.j(TO,e)))],null),$APP.mu,"Unstake",$APP.Eu,function(){$APP.ju();$APP.S(new $APP.J(null,1,5,$APP.K,[$APP.qC],null));$APP.S(new $APP.J(null,1,5,$APP.K,[$APP.UE],null));return $APP.S(new $APP.J(null,1,5,$APP.K,[$APP.rG],null))}],null)],null))}}function b(e){return function(){return $APP.S(new $APP.J(null,2,
5,$APP.K,[$APP.LC,new $APP.h(null,4,[$APP.Bu,$APP.UH,$APP.Cu,new $APP.J(null,1,5,$APP.K,[$APP.uq($APP.Th($APP.Ph,$APP.vj.j(TO,e)))],null),$APP.mu,"Claim",$APP.Eu,function(){$APP.ju();$APP.S(new $APP.J(null,1,5,$APP.K,[$APP.tH],null));return $APP.S(new $APP.J(null,1,5,$APP.K,[$APP.BA],null))}],null)],null))}}function c(e){return function(){return $APP.S(new $APP.J(null,2,5,$APP.K,[$APP.LC,new $APP.h(null,4,[$APP.Bu,$APP.WA,$APP.Cu,new $APP.J(null,1,5,$APP.K,[$APP.uq($APP.Th($APP.Ph,$APP.vj.j(TO,e)))],
null),$APP.mu,"Stake",$APP.Eu,function(){$APP.ju();$APP.S(new $APP.J(null,1,5,$APP.K,[$APP.qC],null));return $APP.S(new $APP.J(null,1,5,$APP.K,[$APP.UE],null))}],null)],null))}}function d(){return $APP.S(new $APP.J(null,6,5,$APP.K,[$APP.zF,new $APP.h(null,2,[$APP.Bu,CP,$APP.Cu,new $APP.J(null,2,5,$APP.K,["0xC2Be9Bf78B2119A995DB49109daA790283399f85",!0],null)],null),$APP.mu,"Approve All NFT",$APP.Eu,function(){$APP.ju();return $APP.S(new $APP.J(null,1,5,$APP.K,[$APP.rG],null))}],null))}return function(){var e=
$APP.p($APP.Ws(new $APP.J(null,1,5,$APP.K,[XO],null)));e=$APP.H(e);var f=$APP.E.j(e,$APP.T),l=$APP.E.j(e,DP),m=$APP.p($APP.Ws(new $APP.J(null,1,5,$APP.K,[tP],null))),r=$APP.z.j(f,cP),t=$APP.Ze($APP.q(m));return new $APP.J(null,6,5,$APP.K,[EP,new $APP.h(null,1,[$APP.Jq,r?"grid-cols-3 gap-4":"grid-cols-1"],null),function(){var w=$APP.Ze(l);return w?new $APP.J(null,3,5,$APP.K,[$APP.Ft,new $APP.h(null,3,[$APP.At,$APP.iu,$APP.Jq,"mr-4",$APP.Ut,d],null),"APPROVE"],null):w}(),function(){if($APP.k(l)){var w=
!r;return w?new $APP.J(null,3,5,$APP.K,[$APP.Ft,new $APP.h(null,4,[$APP.At,$APP.iu,kP,t,$APP.Jq,"mr-4",$APP.Ut,c(m)],null),"ENTER"],null):w}return l}(),$APP.k(l)?r?new $APP.J(null,3,5,$APP.K,[$APP.Ft,new $APP.h(null,4,[$APP.At,$APP.iu,kP,t,$APP.Jq,"mr-4",$APP.Ut,a(m)],null),"FLEE"],null):r:l,$APP.k(l)?r?new $APP.J(null,3,5,$APP.K,[$APP.Ft,new $APP.h(null,3,[kP,t,$APP.Ut,b(m),$APP.At,$APP.PH],null),"CLAIM"],null):r:l],null)}};
$APP.KK=function(a){return $APP.eq(new $APP.h(null,2,[$APP.rD,YO,$APP.Aq,function(){return new $APP.J(null,6,5,$APP.K,[$APP.wz,new $APP.h(null,1,[$APP.qt,new $APP.h(null,2,[$APP.qG,"2%",$APP.vt,"98%"],null)],null),new $APP.J(null,1,5,$APP.K,[hP],null),new $APP.J(null,2,5,$APP.K,[$APP.NJ,new $APP.h(null,1,[$APP.Jq,"my-4"],null)],null),new $APP.J(null,2,5,$APP.K,[GP,new $APP.J(null,2,5,$APP.K,[wP,$APP.R.o($APP.dv.o(a))],null)],null),new $APP.J(null,3,5,$APP.K,[HP,new $APP.J(null,2,5,$APP.K,[BP,$APP.R.o($APP.dv.o(a))],
null),new $APP.J(null,1,5,$APP.K,[FP],null)],null)],null)}],null),$APP.pJ)};xP=$APP.P("fgl.app.views.battlefield","select-all");dP=$APP.O("img.inline-block.ml-6");ZO=$APP.P("fgl.app.views.battlefield","set-type");IP=$APP.O("span.block.ffd");JP=$APP.O("div.absolute.right-0");KP=$APP.O("div.border-4.border-white.absolute.w-full.h-full.p-px");qP=$APP.P("fgl.app.views.battlefield","deselect");LP=$APP.O("borderWidth");MP=$APP.O("borderStyle");pP=$APP.P("fgl.app.views.battlefield","select");AP=$APP.O("span.text-xl.ml-2.fb");
NP=$APP.O("borderColor");OP=$APP.O("div.h-full.bg-transparent.w-full");HP=$APP.O("div.grid.gap-4.mt-4");PP=$APP.O("img.absolute.left-0");vP=$APP.O("ref");QP=$APP.O("position");RP=$APP.O("div.rs1.relative");SP=$APP.O("span.block.fb");fP=$APP.O("unstaked");aP=$APP.O("defaultValue");VO=$APP.O("revealUnstake");TP=$APP.O("borderLeft");kP=$APP.O("disabled");oP=$APP.O("earned?");EP=$APP.O("div.cs3.ce4.rs1.re2.justify-self-end");UP=$APP.O("img.m-auto");jP=$APP.O("selected");VP=$APP.O("div.flex.flex-row.items-center.font-bold");
GP=$APP.O("div.overflow-x-auto");rP=$APP.O("flexShrink");bP=$APP.O("onValueChange");tP=$APP.P("fgl.app.views.battlefield","selected");WP=$APP.P("fgl.app.views.battlefield","type");XP=$APP.O("borderTop");uP=$APP.O("div.overflow-x-auto.flex.pb-8.min-h-21rem");XO=$APP.P("fgl.app.views.battlefield","data");YP=$APP.O("div.absolute.bottom-0");ZP=$APP.O("div.grid.justify-center.items-center.h-full.w-full");eP=new $APP.Lg(null,"staked","staked",-216999638,null);cP=$APP.O("staked");
gP=new $APP.Lg(null,"unstaked","unstaked",-2121273981,null);lP=$APP.O("checked");mP=$APP.O("onCheckedChange");$P=$APP.O("backgroundRepeat");yP=$APP.O("all-selected");CP=$APP.O("setApprovalForAll");nP=$APP.O("image");aQ=$APP.O("borderBottom");bQ=$APP.O("div.rs2.flex.flex-row.justify-between.text-center.items-center");cQ=$APP.O("div.w-0.h-0.absolute.bottom-0.right-0");DP=$APP.O("bf-approved");$APP.ed("battlefield");var $O=$APP.Sm(421,{});var dQ=$APP.Sm(423,{});var zP=function zP(a){switch(arguments.length){case 1:return zP.o(arguments[0]);case 2:return zP.j(arguments[0],arguments[1]);default:throw Error(["Invalid arity: ",$APP.n.o(arguments.length)].join(""));}};zP.o=function(a){return zP.j(a,$APP.F)};
zP.j=function(a,b){var c=$APP.H(a);a=$APP.E.j(c,$APP.vt);c=$APP.E.j(c,$APP.TF);b=$APP.H(b);var d=$APP.E.j(b,$APP.Jq);return new $APP.J(null,6,5,$APP.K,[$APP.Rt,dQ.Root,$APP.ot($APP.B([b,new $APP.h(null,1,[$APP.Jq,["flex flex-row relative items-center",$APP.n.o($APP.k(d)?d:"")].join("")],null)])),new $APP.J(null,2,5,$APP.K,[$APP.wz,new $APP.h(null,1,[$APP.qt,new $APP.h(null,7,[$APP.wt,"rgba(79, 126, 140, 0.231)",$APP.vt,a,$APP.st,a,NP,"rgb(23, 47, 57)",$APP.fH,"0.26rem",MP,"solid",LP,"0.05rem"],null)],
null)],null),c,new $APP.J(null,3,5,$APP.K,[$APP.Rt,dQ.Indicator,new $APP.J(null,2,5,$APP.K,[PP,new $APP.h(null,2,[$APP.qt,new $APP.h(null,3,[$APP.vt,a,$APP.st,a,$APP.rt,"16%"],null),$APP.Wu,"/images/check.svg"],null)],null)],null)],null)};zP.I=2;var iP=function iP(a){switch(arguments.length){case 1:return iP.o(arguments[0]);case 2:return iP.j(arguments[0],arguments[1]);default:throw Error(["Invalid arity: ",$APP.n.o(arguments.length)].join(""));}};iP.o=function(a){return iP.j(a,$APP.F)};
iP.j=function(a,b){var c=$APP.H(a);a=$APP.E.j(c,oP);var d=$APP.E.j(c,cP),e=$APP.E.j(c,jP),f=$APP.E.j(c,kP),l=$APP.E.j(c,$APP.R),m=$APP.E.j(c,$APP.vt),r=$APP.E.j(c,$APP.dz);$APP.E.j(c,$APP.ho);var t=$APP.E.j(c,$APP.CA),w=$APP.E.j(c,mP);c=$APP.E.j(c,nP);m=$APP.k(m)?m:"21.7rem";var y=$APP.at.j($APP.Xs,m);return new $APP.J(null,5,5,$APP.K,[$APP.Rt,dQ.Root,$APP.ot($APP.B([new $APP.h(null,6,[kP,f,$APP.R,l,$APP.fw,l,mP,w,lP,e,$APP.qt,new $APP.h(null,7,[$APP.vt,m,QP,"relative",$APP.st,y(1.29953917),$APP.dH,
"top 53% left 51%",$P,"no-repeat",$APP.gH,"145% 131%",$APP.Wz,'url("/images/card-bg.svg")'],null)],null),b])),new $APP.J(null,3,5,$APP.K,[OP,new $APP.h(null,1,[$APP.qt,new $APP.h(null,1,[$APP.fH,y(.01497696)],null)],null),new $APP.J(null,4,5,$APP.K,[ZP,new $APP.h(null,1,[$APP.qt,new $APP.h(null,4,[XP,"4px transparent",$APP.fH,y(.01497696),$APP.qG,"1%",$APP.Ct,"black 2px 2px 3px"],null)],null),new $APP.J(null,4,5,$APP.K,[RP,new $APP.J(null,2,5,$APP.K,[UP,new $APP.h(null,2,[$APP.qt,new $APP.h(null,
1,[$APP.vt,y(.93894009)],null),$APP.Wu,c],null)],null),new $APP.J(null,3,5,$APP.K,[YP,new $APP.h(null,1,[$APP.qt,new $APP.h(null,6,[$APP.Uu,"linear-gradient(#ffffff00, #000000)",$APP.st,"20%",$APP.tt,"50%",$APP.ut,"translateX(-50%)",$APP.vt,y(.93894009),$APP.fH,y(.01497696)],null)],null),new $APP.J(null,3,5,$APP.K,[SP,new $APP.h(null,1,[$APP.qt,new $APP.h(null,2,[$APP.ut,"translateY(50%)",$APP.Tu,y(.10368664)],null)],null),l],null)],null),$APP.k(d)?new $APP.J(null,3,5,$APP.K,[JP,new $APP.h(null,1,
[$APP.qt,new $APP.h(null,5,[$APP.Wz,'url("/images/staked-tag.png")',$APP.gH,"100%",$APP.vt,y(.29032258),$APP.rt,"5%",$APP.ut,["translateX(",$APP.n.o(y(.01843318)),")"].join("")],null)],null),new $APP.J(null,3,5,$APP.K,[IP,new $APP.h(null,1,[$APP.qt,new $APP.h(null,3,[$APP.Tu,y(.06912442),$APP.DB,"rgb(11, 57, 66)",$APP.ut,"translateY(-5%)"],null)],null),"Staked"],null)],null):d],null),new $APP.J(null,5,5,$APP.K,[bQ,new $APP.h(null,1,[$APP.qt,new $APP.h(null,2,[$APP.Tu,y(.08640553),$APP.qG,["0 ",$APP.n.o(y($APP.k(a)?
.1843318:.13824885))].join("")],null)],null),$APP.k(r)?new $APP.J(null,3,5,$APP.K,[VP,new $APP.J(null,2,5,$APP.K,[$APP.dK,y(.13824885)],null),new $APP.J(null,3,5,$APP.K,[$APP.LJ,r,new $APP.h(null,1,[$APP.qt,new $APP.h(null,1,[$APP.GG,y(.2764977)],null)],null)],null)],null):r,$APP.k(t)?new $APP.J(null,3,5,$APP.K,[VP,new $APP.J(null,2,5,$APP.K,[$APP.MJ,y(.13824885)],null),new $APP.J(null,3,5,$APP.K,[$APP.LJ,t,new $APP.h(null,1,[$APP.qt,new $APP.h(null,1,[$APP.GG,y(.2764977)],null)],null)],null)],null):
t,$APP.k(a)?new $APP.J(null,2,5,$APP.K,[$APP.au,"Earned"],null):a],null)],null)],null),new $APP.J(null,4,5,$APP.K,[$APP.Rt,dQ.Indicator,new $APP.h(null,1,[$APP.Jq,"block absolute top-0 left-0 h-full w-full"],null),new $APP.J(null,3,5,$APP.K,[KP,new $APP.h(null,1,[$APP.qt,new $APP.h(null,1,[$APP.fH,y(.01497696)],null)],null),new $APP.J(null,2,5,$APP.K,[cQ,new $APP.h(null,1,[$APP.qt,new $APP.h(null,4,[XP,[$APP.n.o(y(.06912442))," solid transparent"].join(""),aQ,[$APP.n.o(y(.06912442))," solid transparent"].join(""),
TP,[$APP.n.o(y(.06912442))," solid white"].join(""),$APP.ut,"translateY(33%) translateX(16%) rotate(45deg)"],null)],null)],null)],null)],null)],null)};iP.I=2;$APP.JK={};$APP.wJ.j(ZO,function(a,b){$APP.C(b,0,null);b=$APP.C(b,1,null);return $APP.M.B(a,WP,b,$APP.B([tP,$APP.vl]))});var eQ=$APP.K,fQ;fQ=function(a,b){return $APP.Fs.B($APP.B([$APP.ho,$APP.mr,$APP.Ar,function(c){var d=$APP.Zq($APP.lA,a);return $APP.k(d)?$APP.sr.N(c,$APP.mr,d,b):$APP.Uq.B($APP.Vq,$APP.B(["No cofx handler registered for",a]))}]))}($APP.NA,function(a){$APP.C(a,0,null);$APP.C(a,1,null);a=$APP.C(a,2,null);return new $APP.J(null,2,5,$APP.K,[XO,a],null)});
$APP.Os(xP,new $APP.J(null,1,5,eQ,[fQ],null),function(a,b){a=$APP.H(a);var c=$APP.E.j(a,XO);a=$APP.E.j(a,$APP.Is);$APP.C(b,0,null);b=$APP.C(b,1,null);return $APP.k(b)?(b=$APP.H(c),b=$APP.E.j(b,$APP.dv),new $APP.h(null,1,[$APP.Is,$APP.M.A(a,tP,$APP.fj.j($APP.vl,$APP.vj.j($APP.ho,b)))],null)):new $APP.h(null,1,[$APP.Is,$APP.M.A(a,tP,$APP.vl)],null)});$APP.wJ.j(pP,function(a,b){$APP.C(b,0,null);b=$APP.C(b,1,null);return null==b?$APP.M.A(a,tP,$APP.vl):$APP.M.A(a,tP,$APP.ni.j($APP.E.A(a,tP,$APP.vl),b))});
$APP.wJ.j(qP,function(a,b){$APP.C(b,0,null);b=$APP.C(b,1,null);return $APP.M.A(a,tP,$APP.ty.j($APP.E.A(a,tP,$APP.vl),b))});$APP.Us(tP,$APP.B([function(a){return $APP.E.A(a,tP,$APP.vl)}]));
$APP.Us(XO,$APP.B([function(a,b){$APP.C(b,0,null);b=$APP.C(b,1,null);var c=$APP.z.j(b,$APP.Yz);b=function(){var y=WP.o(a);return $APP.k(y)?y:cP}();var d=$APP.H(a);d=$APP.E.j(d,$APP.yD);var e=$APP.E.j(a,d),f=$APP.H(e),l=$APP.E.j(f,$APP.pD),m=$APP.E.j(f,$APP.iz),r=$APP.FB.o(a),t=$APP.Lz.o(e);f=$APP.TC.o(e);e=$APP.UC.o(e);var w=tP.o(a);c=c?$APP.VF:function(y){return $APP.Ze($APP.VF.o(y))};c=$APP.E.j(new $APP.h(null,2,[cP,$APP.Dj(c,$APP.vj.j(function(y){return $APP.Xp.B($APP.B([$APP.Xp.B($APP.B([new $APP.h(null,
1,[$APP.ho,y],null),$APP.E.j(r,y)])),$APP.E.j(m,y)]))},UO($APP.vj.j(function(y){return y.toString()},l)))),fP,$APP.Dj(c,$APP.vj.j(function(y){return $APP.Xp.B($APP.B([new $APP.h(null,1,[$APP.ho,y],null),$APP.E.j(r,y)]))},UO($APP.vj.j(function(y){return y.toString()},t))))],null),b);l=$APP.z.j($APP.x(w),$APP.x(c));return new $APP.h(null,6,[$APP.T,b,$APP.hB,d,$APP.dI,e,$APP.dv,c,DP,f,yP,l],null)}]));$APP.Le();
}).call(this);