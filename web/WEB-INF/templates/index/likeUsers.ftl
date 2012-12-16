<div class="like">
    <ul class="clearfix">
        <#list upUsers as user>
            <li>
                <a href="javascript:;"><img height="36" width="36" src="http://imgf.lefoto.me${user.getFace()}" /></a>
            </li>
        </#list>
    </ul>
</div>