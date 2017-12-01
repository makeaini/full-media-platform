$(document).ready(function(){
  var playlist = [{
      title:"NiceHidden",
      artist:"CuPlayer.com",
      mp3:"http://localhost:8080/full-media-platform/file/resourcesVideoPlay?fileId=e109484acf4145d8aac3530c088cb042&resourceId=560faaf2bb35bd1db0731967",
    }];
  
  var cssSelector = {
    jPlayer: "#jquery_jplayer",
    cssSelectorAncestor: ".music-player"
  };
  
  var options = {
    swfPath: "Jplayer.swf",
    supplied: "ogv, m4v, oga, mp3"
  };
  
  var myPlaylist = new jPlayerPlaylist(cssSelector, playlist, options);
  
});