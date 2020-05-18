<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="/src/js/jquery-3.3.1.js"></script>
<script src="https://kit.fontawesome.com/8bd2671777.js" crossorigin="anonymous"></script>
<style>
/* PRESS THE MENU BUTTON FOR ANIMATION */
/* PRESS PLAY BUTTON TO LISTEN THE DEMO SONG */

/* As seen on: "https://dribbble.com/shots/2144866-Day-5-Music-Player-Rebound/" */

/* THANK YOU! */
@import
	url(https://fonts.googleapis.com/css?family=Open+Sans:300,400,700);

body {
	background: #ff564c;
	padding: 0;
	margin: 0;
}

@
keyframes harlem { 0% {
	transform: scale(1);
	-webkit-transform: scale(1);
	-moz-transform: scale(1);
	-o-transform: scale(1);
	-ms-transform: scale(1);
}


}
h2 {
	margin: 0;
	padding: 0;
	font-size: 10px;
	line-height: 10px;
	letter-spacing: 2.5px;
	font-family: 'Open Sans';
	font-weight: 700;
	color: #fff;
}

h3 {
	margin: 0;
	padding: 0;
	font-size: 11px;
	line-height: 11px;
	font-family: 'Open Sans';
	font-weight: 400;
	color: #777;
}

h4 {
	margin: 0 0 5px 29px;
	padding: 0;
	font-size: 14px;
	line-height: 14px;
	font-family: 'Open Sans';
	font-weight: 700;
	color: #fff;
}

h5 {
	margin: 0;
	padding: 0;
	font-size: 13px;
	line-height: 13px;
	font-family: 'Open Sans';
	font-weight: 700;
	color: #6d6d6d;
}

h6 {
	margin: 0;
	padding: 0;
	font-size: 13px;
	line-height: 13px;
	font-family: 'Open Sans';
	font-weight: 700;
	color: rgb(240, 240, 240);
}

.screen {
	background-color: #fff;
	padding: 0;
	height: 650px;
	width: 366px;
	position: absolute;
	left: 50%;
	top: 0;
	transform: translate(-50%, 0);
	box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.4);
	overflow: hidden;
	margin: 30px 0;
}

.header {
	display: inline-flex;
	margin: 0 0 40px 0;
}

input[type=checkbox] {
	visibility: hidden;
	margin: 0;
	padding: 0;
	z-index: 10;
}

label.main:before {
	position: absolute;
	top: 23px;
	left: 23px;
	font-family: FontAwesome;
	content: '\f0c9';
	color: #959595;
	font-size: 16px;
	line-height: 16px;
	cursor: pointer;
	z-index: 10;
	transition: all 0.3s ease-in;
}

label.main:hover:before {
	color: #bbb;
}

.screen>#magicButton:checked ~ label.main:before {
	color: #ff564c;
	transition: all 0.3s ease-in;
}

.coverImage {
	background:
		url('/src/imgs/albumImg/아이유-Love poem.jpg')
		no-repeat;
	background-size: cover;
	width: 366px;
	height: 366px;
	padding: 0;
	margin: 0;
	position: absolute;
	opacity: 0.5;
	top: 0;
	left: 0;
	transform-origin: 0% 0%;
	transition: all 0.3s ease-in;
}

.screen>#magicButton:checked ~ .coverImage {
	transform: scale(0.251, 0.251);
	left: 23px;
	top: 60px;
	transition: all 0.3s ease-in;
	border-radius: 20px;
}

.search:before {
	position: absolute;
	top: 23px;
	right: 23px;
	font-family: FontAwesome;
	content: '\f002';
	color: #959595;
	font-size: 16px;
	line-height: 16px;
	cursor: pointer;
	z-index: 10;
	transition: all 0.3s ease-in;
}

.search:hover:before {
	color: #bbb;
}

.bodyPlayer {
	position: absolute;
	top: 366px;
	left: 0;
	margin: 0;
	padding: 0;
	height: 286px;
	width: 366px;
	background: #111;
	transition: all 0.3s ease-in;
	overflow: auto;
}

.screen>#magicButton:checked ~ .bodyPlayer {
	height: 470px;
	left: 0;
	top: 180px;
	transition: all 0.3s ease-in;
}

.list {
	border-spacing: 0px 2px;
	width: 342px;
	visibility: hidden;
	opacity: 0;
	position: absolute;
	top: 378px;
	left: 12px;
	transform-origin: 0% 0%;
	transform: scale(0.1, 0.1);
	transition: all 0.3s ease-in;
	overflow: auto;
}

.list tr {
	transform-origin: 0% 50%;
	height: 50px;
	text-align: center;
	background: #1d1d1d;
	text-indent: 8px;
}

.list tr:hover {
	background: #222;
	cursor: pointer;
}

.title {
	width: 215px;
	text-align: left;
	text-indent: 15px;
	transition: all 0.11s ease-in;
}

.list tr:hover .title {
	padding-left: 5px;
	width: 210px;
	transition: all 0.11s ease-in;
}

.screen>#magicButton:checked ~ .list {
	top: 192px;
	left: 12px;
	opacity: 1;
	visibility: visible;
	transform: scale(1, 1);
	transition: all 0.3s ease-in;
}

/* .screen>#magicButton:checked ~ table tr:nth-child(1) {
	opacity: 1;
	animation: harlem 0.3s linear forwards;
	animation-delay: 0.185s;
}

.screen>#magicButton:checked ~ table tr:nth-child(2) {
	animation: harlem 0.3s linear forwards;
	animation-delay: 0.2s;
}

.screen>#magicButton:checked ~ table tr:nth-child(3) {
	animation: harlem 0.3s linear forwards;
	animation-delay: 0.215s;
}

.screen>#magicButton:checked ~ table tr:nth-child(4) {
	animation: harlem 0.3s linear forwards;
	animation-delay: 0.23s;
}

.screen>#magicButton:checked ~ table tr:nth-child(5) {
	animation: harlem 0.3s linear forwards;
	animation-delay: 0.245s;
}

.screen>#magicButton:checked ~ table tr:nth-child(6) {
	animation: harlem 0.3s linear forwards;
	animation-delay: 0.26s;
}

.screen>#magicButton:checked ~ table tr:nth-child(7) {
	animation: harlem 0.3s linear forwards;
	animation-delay: 0.275s;
}

.screen>#magicButton:checked ~ table tr:nth-child(8) {
	animation: harlem 0.3s linear forwards;
	animation-delay: 0.29s;
}

.screen>#magicButton:checked ~ table tr:nth-child(9) {
	animation: harlem 0.3s linear forwards;
	animation-delay: 0.305s;
} */

/* .checked{
	animation: harlem 0.3s linear forwards;
	animation-delay: 0.305s;
} */

label.zmr:before {
	font-family: FontAwesome;
	content: "\f08a";
	color: #777;
	/* color: #ff564c; */
	font-size: 15px;
	line-height: 15px;
	cursor: pointer;
	z-index: 10;
	transition: all 0.15s linear;
}

/* .heartClick:before{
	content: "\f004";
	color: #ff564c;
	transition: all 0.15s linear;
} */

td>#heart:checked ~ label.zmr:before {
	content: "\f004";
	color: #ff564c;
	transition: all 0.15s linear;
}
/*
td>#heart1:checked ~ label.zmr:before {
	content: "\f004";
	color: #ff564c;
	transition: all 0.15s linear;
}

td>#heart2:checked ~ label.zmr:before {
	content: "\f004";
	color: #ff564c;
	transition: all 0.15s linear;
}

td>#heart3:checked ~ label.zmr:before {
	content: "\f004";
	color: #ff564c;
	transition: all 0.15s linear;
}

td>#heart4:checked ~ label.zmr:before {
	content: "\f004";
	color: #ff564c;
	transition: all 0.15s linear;
}

td>#heart5:checked ~ label.zmr:before {
	content: "\f004";
	color: #ff564c;
	transition: all 0.15s linear;
}

td>#heart6:checked ~ label.zmr:before {
	content: "\f004";
	color: #ff564c;
	transition: all 0.15s linear;
}

td>#heart7:checked ~ label.zmr:before {
	content: "\f004";
	color: #ff564c;
	transition: all 0.15s linear;
}

td>#heart8:checked ~ label.zmr:before {
	content: "\f004";
	color: #ff564c;
	transition: all 0.15s linear;
} */

.bar {
	background: url('http://imgh.us/bar_3.svg') no-repeat;
	background-size: 100%;
	width: 320px;
	height: 21px;
	position: absolute;
	top: 407px;
	left: 23px;
	transform-origin: 0% 0%;
	transition: all 0.3s ease-in;
}

.screen>#magicButton:checked ~ .bar {
	top: 119px;
	left: 126px;
	transform: scale(0.6875, 0.6875);
	transition: all 0.3s ease-in;
}

.info {
	width: 157px;
	position: absolute;
	left: 104px;
	top: 440px;
	transition: all 0.3s ease-in;
}

.screen>#magicButton:checked ~ .info {
	top: 66px;
	left: 126px;
	text-align: left;
	transition: all 0.3s ease-in;
}

.screen>#magicButton:checked ~ .info h4 {
	margin-left: 0;
	font-size: 16px;
	color: #111111;
	transition: all 0.3s ease-in;
}

.player {
	background: none;
	text-align: center;
	font-family: FontAwesome;
	color: #fff;
	font-size: 26px;
	line-height: 26px;
	width: 216px;
	height: 35px;
	position: absolute;
	bottom: 100px;
	left: 48%;
	transform: translate(-50%, 0);
	z-index: 10;
	transition: all 0.3s ease-in;
	border-spacing: 0;
}

.player td {
	width: 72px;
}

.backward:before {
	content: "\f04a";
	cursor: pointer;
	transition: all 0.15s linear;
}

.play:before {
	content: "\f04b";
	cursor: pointer;
	transition: all 0.15s linear;
}

.forward:before {
	content: "\f04e";
	cursor: pointer;
	transition: all 0.15s linear;
}

.backward:hover:before {
	color: #bbb;
	transition: all 0.15s linear;
}

.play:hover:before {
	color: #bbb;
	transition: all 0.15s linear;
}

.forward:hover:before {
	color: #bbb;
	transition: all 0.15s linear;
}

td>#play:checked ~ label.play:before {
	content: "\f04c";
	transition: all 0.15s linear;
}

.screen>#magicButton:checked ~ .player {
	bottom: 4px;
	left: -44px;
	transform: scale(0.45, 0.45);
	transition: all 0.3s ease-in;
}

.shadow {
	background: rgba(17, 17, 17, 0.8);
	width: 366px;
	height: 50px;
	position: absolute;
	bottom: -12px;
	left: 0;
	box-shadow: 0px -15px 40px rgba(17, 17, 17, 0.8);
}

.footer {
	background: none;
	font-family: FontAwesome;
	color: #6d6d6d;
	font-size: 15px;
	line-height: 15px;
	height: 45px;
	width: 360px;
	position: absolute;
	bottom: 10px;
	left: 23px;
	z-index: 20;
	transition: all 0.15s linear;
	transition: bottom 0.3s linear;
}

.love:before {
	content: "\f08a";
	cursor: pointer;
	transition: all 0.15s linear;
}

.shuffle:before {
	content: "\f074";
	cursor: pointer;
	transition: all 0.15s linear;
}

.repeat:before {
	content: "\f01e";
	cursor: pointer;
	transition: all 0.15s linear;
}

.options:before {
	content: "\f141";
	cursor: pointer;
	transition: all 0.15s linear;
}

.love:hover:before {
	color: #bbb;
	transition: all 0.15s linear;
}

.shuffle:hover:before {
	color: #bbb;
	transition: all 0.15s linear;
}

.repeat:hover:before {
	color: #bbb;
	transition: all 0.15s linear;
}

.options:hover:before {
	color: #bbb;
	transition: all 0.15s linear;
}

td>#love:checked ~ label.love:before {
	content: "\f004";
	color: #ff564c;
	transition: all 0.15s linear;
}

td>#shuffle:checked ~ label.shuffle:before {
	color: #ff564c;
	transition: all 0.15s linear;
}

td>#repeat:checked ~ label.repeat:before {
	color: #ff564c;
	transition: all 0.15s linear;
}

.screen>#magicButton:checked ~ .footer {
	bottom: -60px;
	transition: all 0.3s ease-in;
}

.current {
	position: absolute;
	right: 23px;
	bottom: -60px;
	transition: all 0.3s ease-in;
}

.screen>#magicButton:checked ~ .current {
	bottom: 17px;
	transition: all 0.3s ease-in;
}

audio {
	visibility: hidden;
}
</style>
<title>Kairos Music Player</title>
</head>
<body>
	<script>
		// audiotrack
		var audioTrack = 0;
		var songNo = 0;
		// play previous music
		function togglePlayPause() {
			var audio = document.getElementById('audio');
			var playpause = document.getElementById("play");
				if (audio.paused) {
					playpause.title = "Pause";
					audio.play();
				} else {
					playpause.title = "Play";
					audio.pause();
				}
			console.log(audioTrack);
		}
		$(function(){
			$('.zmr').click(function(){
				// $(this).parent().parent().addClass(".checked");
				console.log(this);
				$(this).toggleClass('heartClick');
			});
		});
		$(function(){
			$('.song').click(function(){
				var audio = $('#audio')[0];
				$('.song').each(function(){
					$(this).find('h6').css('color','rgb(240, 240, 240)');
				});
				$(this).find('h6').css('color','rgb(255, 86, 76)');
				audioTrack = $(this).index();
				//play the track audioTrack
				$('#audio').attr("src","/src/songs/"+$("#"+$(this).index()).val()+".mp3");
				audio.play();
			});
		});


		function prevMusic(){
			var trackNo = document.getElementById(audioTrack);
			var file = trackNo.value;
			var audio = document.getElementById('audio');
			audio.src="/src/songs/"+file+".mp3";
			audio.play();
			// audioTrack -= 1;
			audioTrack--;
		}
		// autoplay next music
		function nextMusic(){
			console.log(audioTrack);
			var trackNo = document.getElementById(audioTrack);
			var file = trackNo.value;
			var audio = document.getElementById('audio');
			// alter soundtrack
			audio.src = '/src/songs/'+file+'.mp3';
			audio.play();
			audioTrack = audioTrack +1;
			
		}
	</script>
	<article class="screen">
		<input type="checkbox" value="None" id="magicButton" name="check" />
		<label class="main" for="magicButton"></label>

		<div class="coverImage"></div>
		<div class="bodyPlayer"></div>

		<table class="list">
			<c:forEach items="${playList}" var='p'>
				<tr class="song">
					<input type="hidden" id= ${p.orderNo-1} value=${p.filepath}>
					<td class="nr">
						<h5>
							${p.orderNo }
							<h5>
					</td>
					<td class="title"><h6>
							${p.songTitle}
							<h6></td>
					<td class="length"><h5>
							
							<h5></td>
					<c:if test="${p.liked eq 1}">
						<td><input type="checkbox" id="heart${p.orderNo}" checked/><label class="zmr"
							for="heart"></label></td>
					</c:if>
					<c:if test="${p.liked eq 0}">
						<td><input type="checkbox" id="heart${p.orderNo}"/><label class="zmr"
							for="heart"></label></td>
					</c:if>
				</tr>
			</c:forEach>

			<tr class="song">
				<td class="nr"><h5>
						2
						<h5></td>
				<td class="title"><h6 style="color: rgb(255, 86, 76);">
						StressedOut
						<h6></td>
				<td class="length"><h5>
						3:22
						<h5></td>
				<td><input type="checkbox" id="heart1" checked /><label
					class="zmr" for="heart1"></label></td>
			</tr>
		</table>

		<div class="shadow"></div>

		<div class="bar"></div>

		<div class="info">
			<h4>'${playList[0].songTitle}'</h4>
			<h3>'${playList[0].songArtist}'</h3>
		</div>
		<audio id="audio" controls onended="nextMusic()">
			<source src="/src/songs/1.mp3" type="audio/mpeg">
		</audio>
		<table class="player">
			<td><input type="checkbox" id="backward" onclick="prevMusic();"/><label
				class="backward" for="backward"></label></td>

			<td><input type="checkbox" id="play" title="Play"
				onclick="togglePlayPause();" /><label class="play" for="play"></label></td>

			<td><input type="checkbox" id="forward" onclick="nextMusic();"/><label
				class="forward" for="forward"></label></td>
		</table>
		<table class="footer">
			<td>
				<input type="checkbox" id="love" checked/><label
				class="love" for="love"></label>
			</td>
			<td>
				<input type="checkbox" id="shuffle" /><label
				class="shuffle" for="shuffle"></label>
			</td>
			<td>
				<input type="checkbox" id="repeat" /><label
				class="repeat" for="repeat"></label>
			</td>
			<td>
				<input type="checkbox" id="options" /><label
				class="options" for="options"></label>
			</td>
		</table>
		<div class="current">
			<h2>'${playList[0].songTitle}'</h2>
		</div>
	</article>
</body>
</html>