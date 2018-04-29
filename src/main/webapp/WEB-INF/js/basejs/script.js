$(document).ready(function() {

	var hh = document.documentElement.clientHeight;
	var ls = document.documentElement.clientWidth;
	if (ls < 640) {



		$(".select dt").click(function() {
			if ($(this).next("div").css("display") == 'none') {
				$(".theme-popover-mask").height(hh);
				$(".theme-popover").css("position", "fixed");
				$(this).next("div").slideToggle("slow");
				$(".select div").not($(this).next()).hide();
			}
          else{
          	$(".theme-popover-mask").height(0);
			$(".theme-popover").css("position", "static");
			$(this).next("div").slideUp("slow");;
          }

		})


		$(".eliminateCriteria").live("click", function() {
			$(".dd-conent").hide();
		})

		$(".select dd").live("click", function() {
			$(".theme-popover-mask").height(0);
			$(".theme-popover").css("position", "static");
			$(".dd-conent").hide();
		});
		$(".theme-popover-mask").live("click", function() {
			$(".theme-popover-mask").height(0);
			$(".theme-popover").css("position", "static");
			$(".dd-conent").hide();
		});

	}


	$("span.love").click(function() {
		$(this).toggleClass("active");
	});


	$("#select1 dd").click(function() {
		if(!$(this).hasClass("selected")) {
			if ($(this).hasClass("select-all")) {
                $("#select2").empty();
                $("#select3").empty();
                $("#selectA").remove();
                $("#selectB").remove();
                $("#selectC").remove();
                return;
            }
			getSecendCategory($(this).attr("category"));
		}
		$(this).addClass("selected").siblings().removeClass("selected");
		if ($(this).hasClass("select-all")) {
			$("#selectA").remove();
            $("#selectB").remove();
            $("#selectC").remove();
		} else {
			var copyThisA = $(this).clone();
			if ($("#selectA").length > 0) {
                $("#selectA").remove();
                $(".select-result dl").append(copyThisA.attr("id", "selectA"));

                // $("#selectA a").html($(this).text());
			} else {
				$(".select-result dl").append(copyThisA.attr("id", "selectA"));

			}
		}
	});
	function getSecendCategory(categoryId) {
        var category = {"categoryId": categoryId}
        $.ajax({
            url: "/getSubCategory",
            data: category,
			success: function (result) {
            	var secend = "<dt class=\"am-badge am-round\">二级分类</dt>\n" +
                    "                                <div class=\"dd-conent\">\n" +
                    "                                    <dd id=\"category2\" class=\"select-all selected\"><a href=\"javascript:;\">全部</a></dd>\n" +
                    "                                "
				$("#select2").empty();
                var jsonData = JSON.parse(result);
                $.each(jsonData, function (i) {
                    var dd = "<dd id=\"" + jsonData[i].id + "\" category=\"" + jsonData[i].id + "\"><a href=\"javascript:;\" category=\"" + jsonData[i].id + "\">" + jsonData[i].name + "</a></dd>\n";
                    secend += dd;
                    $('#' + jsonData[i].id).live('click', select2ClickFunction);
                })
                $('#category2').live('click', select2ClickFunction);

                secend+="</div>";
                // alert(secend);
                $("#select2").append(secend);
            }
        });
    }

	$("#select2 dd").click(function() {
		select2ClickFunction()
	});
	function select2ClickFunction() {
        if(!$(this).hasClass("selected")) {
            if ($(this).hasClass("select-all")) {
                $("#select3").empty();
                $("#selectB").remove();
                $("#selectC").remove();
                return;
            }
            getThirdCategory($(this).attr("category"));
        }
        $(this).addClass("selected").siblings().removeClass("selected");
        if ($(this).hasClass("select-all")) {
            $("#selectB").remove();
            $("#selectC").remove();
        } else {
            var copyThisB = $(this).clone();
            if ($("#selectB").length > 0) {
                $("#selectB").remove();
                $(".select-result dl").append(copyThisB.attr("id", "selectB"));
                // $("#selectB a").html($(this).text());
            } else {
                $(".select-result dl").append(copyThisB.attr("id", "selectB"));
            }
        }
    }
    function getThirdCategory(categoryId) {
        var category = {"categoryId": categoryId}
        $.ajax({
            url: "/getSubCategory",
            data: category,
            success: function (result) {
                var secend = "<dt class=\"am-badge am-round\">三级分类</dt>\n" +
                    "                                <div class=\"dd-conent\">\n" +
                    "                                    <dd id=\"category3\" class=\"select-all selected\"><a href=\"javascript:;\">全部</a></dd>\n" +
                    "                                "
                $("#select3").empty();
                var jsonData = JSON.parse(result);
                $.each(jsonData, function (i) {
                    var dd = "<dd id=\"" + jsonData[i].id + "\" category=\"" + jsonData[i].id + "\"><a href=\"javascript:;\" category=\"" + jsonData[i].id + "\">" + jsonData[i].name + "</a></dd>\n";
                    secend+=dd;
                    $('#' + jsonData[i].id).live('click', select3ClickFunction);
                });
                $('#category3').live('click', select3ClickFunction);
                secend+="</div>";
                // alert(secend);
                $("#select3").append(secend);
            }
        });
    }
	$("#select3 dd").click(function() {
        select3ClickFunction();
	});
	function select3ClickFunction() {
        $(this).addClass("selected").siblings().removeClass("selected");
        if ($(this).hasClass("select-all")) {
            $("#selectC").remove();
        } else {
            var copyThisC = $(this).clone();
            if ($("#selectC").length > 0) {
                $("#selectC").remove();
                $(".select-result dl").append(copyThisC.attr("id", "selectC"));

                // $("#selectC a").html($(this).text());
            } else {
                $(".select-result dl").append(copyThisC.attr("id", "selectC"));
            }
        }
    }
	$("#selectA").live("click", function() {
		$(this).remove();
		$("#select1 .select-all").addClass("selected").siblings().removeClass("selected");
	});

	$("#selectB").live("click", function() {
		$(this).remove();
		$("#select2 .select-all").addClass("selected").siblings().removeClass("selected");
	});

	$("#selectC").live("click", function() {
		$(this).remove();
		$("#select3 .select-all").addClass("selected").siblings().removeClass("selected");
	});

	$(".select dd").live("click", function() {
		if ($(".select-result dd").length > 1) {
			$(".select-no").hide();
			$(".eliminateCriteria").show();
			$(".select-result").show();
		} else {
			$(".select-no").show();
			$(".select-result").hide();

		}
	});

	$(".eliminateCriteria").live("click", function() {
		$("#selectA").remove();
		$("#selectB").remove();
		$("#selectC").remove();
		$(".select-all").addClass("selected").siblings().removeClass("selected");
		$(".eliminateCriteria").hide();
		$(".select-no").show();
		$(".select-result").hide();

	});






});