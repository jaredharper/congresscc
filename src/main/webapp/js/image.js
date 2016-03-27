/*
 * https://github.com/jamescharlesworth/jQuery-Image-Loader-Callback
 * 
 * With some changes (added attempts counter and increased callback timeout to 500ms, otherwise
 * for missing images it will attempt an infinite number of almost unthrottled connections)
 */
(function( $ ) {
  $.fn.loadImage = function(src,cb,image,attempts) {

    var self = this,
        dataSrc = $(self).attr("data-src");
      
    image = image || new Image();
    cb = cb || function() {};
    attempts = attempts || 1;

    if (typeof src === "undefined") {
      if (dataSrc.length) {
          src = dataSrc;
      } else {
           throw new Error("You must specify the data-src on the html element or pass an image src path to loadImage()");
      }
    }
    setTimeout(function() {
      if (image.src != src)
        image.src = src;
      if (attempts > 5) {
    	  self.attr("src","img/error.jpg");
    	  cb.call(self);
    	  return;
      }
      if (!image.complete)
        return self.loadImage(src,cb,image,attempts+1);
      self.attr('src',src);
      cb.call(self);
    },500);
  };
})( jQuery );
