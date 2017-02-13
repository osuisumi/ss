<#macro aceditor  theme="eclipse" mode="html" selectors=[]> 
<script type="text/javascript" src="${ctx}/js/ace/ace.js"></script>
<script>
function inject(options,selector, callback) {    
        ace.config.loadModule("ace/ext/textarea", function() {
            var event = ace.require("ace/lib/event");
            var areas = $(selector);
            for (var i = 0; i < areas.length; i++) {
                event.addListener(areas[i], "click", function(e) {
                    if (e.detail == 3) {
                        ace.transformTextarea(e.target, options.ace);
                    }
                });
            }
            callback && callback();
        });
}


<#list selectors as selector>
inject({},"${selector}", function () {
    var t = document.querySelector("${selector}");
    var textAce_${selector_index} = ace.require("ace/ext/textarea").transformTextarea(t);
    textAce_${selector_index}.getSession().on('change', function(e) {
   		  $("${selector}").val(textAce_${selector_index}.getValue());
	});
});
</#list>
</script>
</#macro>