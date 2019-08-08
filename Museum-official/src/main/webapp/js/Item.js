$(function(){
    function parseUrl() {
        let searchHref = window.location.search.replace('?', '');
        let params = searchHref.split('&');
        let returnParam = {};
        params.forEach(function(param) {
          let paramSplit = param.split('=');
          returnParam[paramSplit[0]] = paramSplit[1];
        });
        return returnParam;
      }

      let id=parseUrl().id
      let type=parseUrl().type
    function check(str) {
        str = str.toString();
        if (str.length < 2) {
            str = '0' + str;
        }
        return str;
    }
    function formatDate(time) {
        var date = new Date(time);
        var year = date.getFullYear(),
            month = date.getMonth() + 1, //月份是从0开始的
            day = date.getDate(),
            hour = check(date.getHours()),
            min = check(date.getMinutes()),
            sec = check(date.getSeconds());
        var newTime = year + '-' +
            month + '-' +
            day + ' ' +
            hour + ':' +
            min + ':' +
            sec;
        return newTime;
    }
    function handleAddTime(result){
        let list =[result.info.essayInfo]
        for (let i in list){
            let addTime=formatDate(list[i].addTime)
            list[i].addTime=addTime
        }
        return list
    }

    $.ajax({
        type: "post",
        url: "/official/essay/getEssayById",
        contentType: "application/json;charset=utf-8",
        data:JSON.stringify({"essayType":parseInt(type),"id":parseInt(id)}),
        dataType: "json",
        success: function (res) {
            let item=handleAddTime(res)
            $.each(item, (index, val)=> {
                $('#header').append(`<h4 class="text-danger text-center">${val.title}</h4>
                <div class="text-center">
                    <ul class="list-inline">
                        <li>录入者:${val.inputer}</li>
                        <li>时间:${val.addTime}</li>
                        <li>作者:${val.author}</li>
                        <li>来源:${val.source}</li>
                        <li>浏览:${val.views}次</li>
                    </ul>
                </div>`)
                $('#content').append(val.content)
            });
            let npList=[
                {
                    nextId:res.info.LowerId,
                    previousId:res.info.upperId,
                    nextTitle:res.info.LowerTitle,
                    previousTitle:res.info.upperTitle
                }
            ]
            $.each(npList, function (index, val) {
                if(!val.nextId && val.previousId){
                    $('#content').append(`<nav >
                <ul class="pager">
                  <li class="previous"><a href="./Item.html?type=${type}&id=${val.previousId}"><span aria-hidden="true">&larr;</span>${val.previousTitle}</a></li>
                </ul>
              </nav>`)
                }else if(!val.previousId && val.nextId){
                    $('#content').append(`<nav >
                    <ul class="pager">
                      <li class="next"><a href="./Item.html?type=${type}&id=${val.nextId}"><span aria-hidden="true">&rarr;</span>${val.nextTitle}</a></li>
                    </ul>
                  </nav>`)
                }else if(val.nextId && val.previousId){
                    $('#content').append(`<nav >
                    <ul class="pager">
                      <li class="previous"><a href="./Item.html?type=${type}&id=${val.previousId}"><span aria-hidden="true">&larr;</span>${val.previousTitle}</a></li>
                      <li class="next"><a href="./Item.html?type=${type}&id=${val.nextId}"><span aria-hidden="true">&rarr;</span>${val.nextTitle}</a></li>
                    </ul>
                  </nav>`)
                }
            });
        }
    });

    $.ajax({
        type: "post",
        url: "/official/essay/latest",
        contentType: "application/json;charset=utf-8",
        data:JSON.stringify({"essayType":parseInt(type)}),
        dataType: "json",
        success: function (res){
            let newList=res.info
            $.each(newList, function (index, val) {
                $('#newList').append(`<li class="list-group-item">
                <span class="glyphicon glyphicon-heart-empty" style="font-size:12px;color:red;"></span>
                <a href="./Item.html?type=${type}&id=${val.id}" title="${val.title}">${val.title}</a>
              </li>`)
            });
        }
    })

    $.ajax({
        type: "post",
        url: "/official/essay/hot",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify({"essayType":parseInt(type)}),
        dataType: "json",
        success: function (res) {
        let hotList=res.info;
        $.each(hotList, function (index, val) { 
            $('#hotList').append(`<li class="list-group-item">
                 <span class="glyphicon glyphicon-header" style="font-size:12px;color:red;"></span>
                 <a href="./Item.html?type=${type}&id=${val.id}" title="${val.title}">${val.title}</a>
               </li>`)
       });
        }
    });

})