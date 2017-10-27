
    ;(function () {
        window.setMeta = function (name, val) {
          var meta = document.querySelectorAll('meta[property="' + name + '"], meta[name="' + name + '"]')
          if (!meta.length) {
            meta = document.createElement('meta')
            meta.name = name
            document.head.appendChild(meta)
          }
         meta[0].content = val || ''
        }
        window.getMeta = function (name) {
          var meta = document.querySelectorAll('meta[property="' + name + '"], meta[name="' + name + '"]')
          if (!meta.length) {
            return ''
          } else {
            return meta[0].content
          }
        }
        !getMeta('weixin:chat_title') && setMeta('weixin:chat_title', document.title)
        !getMeta('weixin:timeline_title') && setMeta('weixin:timeline_title', document.title)
        !getMeta('weixin:description') && setMeta('weixin:description', getMeta('og:description'))
    })();
    ;
        function resizeIframe (frame_id, height, width) {
            var adIframe = document.getElementById(frame_id);
            if (adIframe) {
                var _height = height;
                var _width = width;

                try {
                    var adIframeBody = adIframe.contentDocument.body;
                    var _height = height || Math.max(adIframeBody.clientHeight, adIframeBody.scrollHeight);
                    var _width = width || Math.max(adIframeBody.clientWidth, adIframeBody.scrollWidth);
                } catch (e) {}

                var adIframeParentBox = $(adIframe).parents('div')[0];
                var hasIcon = !$(adIframeParentBox).hasClass('Advertisement');
                if (hasIcon) {
                    $(adIframeParentBox).width(_width).height(_height);
                }
                $(adIframe).css({
                  width: _width + 'px',
                  height: _height + 'px'
                })
                adIframe.height = _height;
                adIframe.width = _width;
            }
        };
    ;
    ;(window.DoubanAdSlots = window.DoubanAdSlots || []).push('dale_talion_subject_movie_top')
    window.DoubanAdLoaders = window.DoubanAdLoaders || {};
    window.adElements = window.adElements || [];
    window.adElements.push({'id': '', 'el': 'dale_talion_subject_movie_top'});
    window.addEventListener('load', function () {
        resizeIframe('dale_talion_subject_movie_top_frame')
    })
    window.addEventListener('message', function (e) {
        var payload = e.data.payload;
        if (payload && payload.height) {
            resizeIframe(payload.source, payload.height, payload.width);
        }
    })
    ;
    ;(window.DoubanAdSlots = window.DoubanAdSlots || []).push('dale_talion_subject_movie_center')
    window.DoubanAdLoaders = window.DoubanAdLoaders || {};
    window.adElements = window.adElements || [];
    window.adElements.push({'id': '', 'el': 'dale_talion_subject_movie_center'});
    window.addEventListener('load', function () {
        resizeIframe('dale_talion_subject_movie_center_frame')
    })
    window.addEventListener('message', function (e) {
        var payload = e.data.payload;
        if (payload && payload.height) {
            resizeIframe(payload.source, payload.height, payload.width);
        }
    })
    ;
    ;(function(){
      
  var _DEFAULT_COVER = "https://img3.doubanio.com/f/talion/edb4a934937733a0163b69e6cc3ad3f689c92d1c/pics/card/defaults/cover.png"
  var _DEFAULT_AVATAR = "https://img3.doubanio.com/f/talion/9c85529e7a0fbe585a2091edd8b9751a1db10ca9/pics/card/defaults/avatar.jpg"

      Date.now||(Date.now=function(){return(new Date).getTime()}),function(){"use strict";for(var e=["webkit","moz"],t=0;t<e.length&&!window.requestAnimationFrame;++t){var n=e[t];window.requestAnimationFrame=window[n+"RequestAnimationFrame"],window.cancelAnimationFrame=window[n+"CancelAnimationFrame"]||window[n+"CancelRequestAnimationFrame"]}if(/iP(ad|hone|od).*OS 6/.test(window.navigator.userAgent)||!window.requestAnimationFrame||!window.cancelAnimationFrame){var i=0;window.requestAnimationFrame=function(e){var t=Date.now(),n=Math.max(i+16,t);return setTimeout(function(){e(i=n)},n-t)},window.cancelAnimationFrame=clearTimeout}}(),function(e,t){"function"==typeof define&&define.amd?define([],t):"object"==typeof exports?module.exports=t():e.Lazify=e.Lazify||t()}(window,function(){return function(e){function t(){return window.scrollY||window.pageYOffset}function n(){w=t(),i()}function i(){l||(requestAnimationFrame(function(){s()}),l=!0)}function r(e){return e.getBoundingClientRect().top+w}function o(e){const t=w,n=t+m,i=r(e),o=i+e.offsetHeight,a=h.threshold/100*m;return o>=t-a&&i<=n+a}function a(e,t){"IMG"===e.tagName.toUpperCase()?e.setAttribute("src",t):e.style.backgroundImage='url("'+t+'")'}function c(e){return e&&(e.match(/^http/)||e.match(/^\//))}function u(e){try{return new Event(e)}catch(n){var t=document.createEvent("Event");return t.initEvent(e,!0,!0),t}}function d(e){function t(){a(e,n),o.removeEventListener("load",t),e.addEventListener("load",function t(n){e.removeEventListener("load",t);var i=u("lazyload");i.originalEvent=n,e.dispatchEvent(i)})}var n=e.getAttribute(h.selector);if(c(n)){if(h.defaultPics){var i=e.getAttribute(h.typeAttr)||"cover",r=h.defaultPics[i];r&&a(e,r)}var o=document.createElement("img");o.addEventListener("load",t),o.addEventListener("error",function(){o.removeEventListener("load",t)}),o.src=n,e.removeAttribute(h.selector),f()}}function s(){return m=window.innerHeight,v.forEach(function(e){o(e)&&d(e)}),l=!1,this}function f(){return v=Array.prototype.slice.call(document.querySelectorAll("["+h.selector+"]")),this}e=e||{};var l,m,w=t(),v=[],h={selector:e.selector||"data-src",threshold:e.threshold||0,defaultPics:e.defaultPics,typeAttr:e.typeAttr||"data-type"};this.check=s,this.update=f;var A=document.querySelector(".page");A.addEventListener("scroll",n),["scroll","resize"].forEach(function(e){window.addEventListener(e,n)})}});
        var lazify = new Lazify({
          defaultPics: {
            avatar: _DEFAULT_AVATAR,
            cover: _DEFAULT_COVER,
          },
          threshold: 10,
        })
        $(function(){
            lazify.update().check()
        })
    })();
  ;
    ;(window.DoubanAdSlots = window.DoubanAdSlots || []).push('dale_talion_subject_movie_after_reviews')
    window.DoubanAdLoaders = window.DoubanAdLoaders || {};
    window.adElements = window.adElements || [];
    window.adElements.push({'id': '', 'el': 'dale_talion_subject_movie_after_reviews'});
    window.addEventListener('load', function () {
        resizeIframe('dale_talion_subject_movie_after_reviews_frame')
    })
    window.addEventListener('message', function (e) {
        var payload = e.data.payload;
        if (payload && payload.height) {
            resizeIframe(payload.source, payload.height, payload.width);
        }
    })
    ;
        var THEME_IDS = '16,10,19,20,6,15,12';
        'use strict';

;(function () {
    var THEMES_API = 'https://m.douban.com/rexxar/api/v2/selection/themes/explore';
    var PROMOS_API = 'https://m.douban.com/rexxar/api/v2/promos?page=selection';
    var URI_PREFIX = 'douban://douban.com';
    var HTTPS_DOUBAN = 'https://m.douban.com';
    var DISPATCH_URL = 'https://www.douban.com/doubanapp/dispatch?uri={uri}&download=1&channel={channel}';

    var root = $('#ThemesWidget ul');
    var TEMPLATE = $('#template-theme-item').html();

    var format = function format(TEMPLATE, data) {
        return TEMPLATE.replace(/{([^{|^}]+)}/g, function (_, key) {
            return data[key] || '';
        });
    };

    var is_client_uri = function is_client_uri(uri) {
        return uri && uri.indexOf(URI_PREFIX) > -1;
    };

    var client_count = 1;
    var exceed_max_client_count = function exceed_max_client_count(is_client) {
        is_client && client_count++;
        return client_count > 3;
    };

    var normalizeURL = function normalizeURL(uri) {
        var url = '';
        if (is_client_uri(uri)) {
            // client uri
            url = format(DISPATCH_URL, {
                uri: uri.replace(URI_PREFIX, ''),
                channel: 'neo_content'
            });
        } else {
            // web url
            url = uri + '?from=mdouban';
        }
        return url;
    };

    $.getJSON(PROMOS_API, function (res) {
        var promos = res.promos.filter(function (promo) {
            return is_client_uri(promo.uri);
        });
        if (!promos.length) return;
        var promo = promos[0];
        var is_client = is_client_uri(promo.uri);
        exceed_max_client_count(is_client);
        var content = format(TEMPLATE, {
            title: promo.text,
            desc: promo.tag,
            url: normalizeURL(promo.uri),
            cover: promo.image,
            extra_className: is_client ? 'is_client' : ''
        });
        root.prepend(content);
    });

    $.getJSON(THEMES_API, {
        themes: THEME_IDS,
        for_mobile: 1
    }, function (res) {
        var content = res.items.sort(function (a, b) {
            return a.target.uri.indexOf(URI_PREFIX) > b.target.uri.indexOf(URI_PREFIX) ? -1 : 1;
        }).slice(0, 4).reduce(function (html, item) {
            var is_client = is_client_uri(item.target.uri);
            var exceeded = exceed_max_client_count(is_client);
            var data = {
                title: item.title,
                desc: item.target.desc,
                url: normalizeURL(exceeded ? item.target.url : item.target.uri),
                cover: item.target.cover_url,
                extra_className: is_client && !exceeded ? 'is_client' : ''
            };
            return html + format(TEMPLATE, data);
        }, '');
        root.append(content);
    });
})();
    ;
    ;(window.DoubanAdSlots = window.DoubanAdSlots || []).push('dale_talion_subject_movie_bottom')
    window.DoubanAdLoaders = window.DoubanAdLoaders || {};
    window.adElements = window.adElements || [];
    window.adElements.push({'id': '', 'el': 'dale_talion_subject_movie_bottom'});
    window.addEventListener('load', function () {
        resizeIframe('dale_talion_subject_movie_bottom_frame')
    })
    window.addEventListener('message', function (e) {
        var payload = e.data.payload;
        if (payload && payload.height) {
            resizeIframe(payload.source, payload.height, payload.width);
        }
    })
    ;
    ;(window.DoubanAdSlots = window.DoubanAdSlots || []).push('dale_tailon_movie_bottom_floating_inner')
    window.DoubanAdLoaders = window.DoubanAdLoaders || {};
    window.adElements = window.adElements || [];
    window.adElements.push({'id': '', 'el': 'dale_tailon_movie_bottom_floating_inner'});
    window.addEventListener('load', function () {
        resizeIframe('dale_tailon_movie_bottom_floating_inner_frame')
    })
    window.addEventListener('message', function (e) {
        var payload = e.data.payload;
        if (payload && payload.height) {
            resizeIframe(payload.source, payload.height, payload.width);
        }
    })
    ;
      'use strict';

$(function () {
  var $bottomAd = $('.bottom_ad_download');

  $bottomAd.on('click', '.close', function (e) {
    e.preventDefault();
    $bottomAd.removeClass('show');
  });
});
    ;
        ;(function() {
            function getBtn() {
                return document.querySelector('.open-in-app-float')
            }
            var $btn = getBtn()
            var winHeight = window.innerHeight
            // Fix wechat browser issue in some iOS device
            var visible = false
            var $page = document.querySelector('.page')
            var bottomBarHeight = null
            function onScroll(e) {
                const scrollY = window.scrollY || $page.scrollTop
                if (!visible && scrollY > winHeight) {
                    visible = true
                    if (bottomBarHeight === null) {
                        var $bar = document.querySelector('.comment-button') || document.querySelector('.open-in-app-fixed-bottom')
                        if ($bar) {
                            bottomBarHeight = 50
                        } else {
                            bottomBarHeight = 0
                        }
                    }
                    getBtn().style.bottom = (25 + bottomBarHeight) + 'px'
                } else if (visible && scrollY < winHeight) {
                    visible = false
                    getBtn().style.bottom = '-50px'
                }
            }
            window.addEventListener('scroll', onScroll)
            $page.addEventListener('scroll', onScroll)

        }());
    ;
      ;(function(){
          'use strict';

var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function (obj) { return typeof obj; } : function (obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; };

;(function (doc, $) {

  function htmlEntities(unsafeText) {
    if (!unsafeText) return '';
    return unsafeText.replace(/&/g, '&amp;').replace(/"/g, '&quot;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
  }

  function linkify(text) {
    var _ref = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : {},
        _ref$br = _ref.br,
        br = _ref$br === undefined ? true : _ref$br,
        _ref$hashtag = _ref.hashtag,
        hashtag = _ref$hashtag === undefined ? true : _ref$hashtag;

    if (!text) return text;
    text = htmlEntities(text);
    if (br) text = text.replace(/\n/g, '<br/>');
    if (hashtag) text = text.replace(/#([^#]+?)#/g, '<a href="/hashtag/$1">#$1#</a>');
    text = text.replace(/(http[s]?:\/\/dou\.bz\/\w+)/g, '<a href="$1">$1</a>');

    return text;
  }

  function stripTags(html) {
    if (!html) return '';
    return html.replace(/<[^>]*?>/g, '');
  }

  function format(str) {
    for (var _len = arguments.length, args = Array(_len > 1 ? _len - 1 : 0), _key = 1; _key < _len; _key++) {
      args[_key - 1] = arguments[_key];
    }

    var ret = void 0;
    if (!str) {
      ret = '';
    } else if (args.length === 1 && _typeof(args[0]) === 'object') {
      ret = str.replace(/{{(\w+?)}}/g, function (match, g1) {
        var map = args[0];
        return g1 in map ? map[g1] : match;
      });
    } else {
      ret = str.replace(/{{(\d+?)}}/g, function (match, g1) {
        var i = +g1 - 1;
        return i >= 0 && i < args.length ? args[i] : match;
      });
    }
    return ret;
  }
  ;
  var curUrl = document.location.href,
      CK = get_cookie('ck'),
      REXXAR_API = 'https://m.douban.com/rexxar/api/v2',
      STATUS = {
    'wish': 'mark',
    'do': 'doing',
    'collect': 'done'
  },
      STATUS_TEXT_MAP = {
    'tv': { thing: '这部电视剧', status: {
        mark: '想看', doing: '在看', done: '看过'
      } },
    'movie': { thing: '这部电影', status: {
        mark: '想看', done: '看过'
      } },
    'music': { thing: '这张唱片', status: {
        mark: '想听', doing: '在听', done: '听过'
      } },
    'book': { thing: '这本书', status: {
        mark: '想读', doing: '在读', done: '读过'
      } },
    'app': { thing: '这个应用', status: {
        mark: '想要', done: '用过'
      } },
    'game': { thing: '这个游戏', status: {
        mark: '想玩', done: '玩过'
      } }
  },
      ISSCROLL = 'is-scrolling-disabled',
      HIDE = 'hide',
      DISABLE = 'disable',
      ERR = 'error',
      $html = $('html'),
      $dialog = $('div.douban-dialog'),
      $tagComment = $('section.subject-mark'),
      $inputTag = $('#input-tag'),
      id = $tagComment.attr('data-id'),
      type = $tagComment.attr('data-type'),
      isBlur = true;

  function bindEvent() {
    $tagComment.on('click', 'a.update-btn', function (e) {
      e.preventDefault();
      var $self = $(this);
      getDialog($self.attr('data-status'));
    }).on('click', 'a.delete-btn', function (e) {
      e.preventDefault();
      if (confirm('确定删除吗？')) {
        ajaxFn('/j/interest/remove_interest', { ck: CK, subjectId: id, type: type }, function (data) {
          data = $.parseJSON(data);
          $tagComment.html(data.html);
        });
      }
    }).on('click', 'div.mark-item a', function () {
      var $self = $(this),
          user = $('#user').val();
      if ($self.hasClass(DISABLE)) {
        return;
      }
      if (user) {
        var mark = $self.attr('name').split('-')[2];
        getDialog(mark);
      } else {
        goLogin();
      }
    });

    $dialog.on('click', 'a.btn-ok', function (e) {
      e.preventDefault();
      var $self = $(this),
          tags = [];
      if ($self.hasClass(DISABLE)) {
        return;
      }
      $self.addClass(DISABLE);

      var comment = $('textarea', $dialog).val(),
          star = $('div.star span.rating-star-max-full', $dialog).length,
          url = null,
          data = {},
          attrStatus = $self.attr('data-status'),
          status = STATUS[attrStatus],
          shareWeibo = $('input[name=sina]', $dialog).is(':checked') ? 'sina' : '',
          shareDouban = $('input[name=douban]', $dialog).is(':checked') ? 'douban' : '';

      if ($dialog.hasClass(ERR) || $('.remark', $dialog).hasClass(ERR)) {
        $self.removeClass(DISABLE);
        return;
      }

      tags = getElVal($('div.remark a.selected'));

      data = {
        ck: CK,
        tags: tags,
        comment: comment,
        sync_douban: shareDouban ? 1 : 0
      };
      if (shareWeibo) {
        data = $.extend({}, data, { 'sync_weibo': 1 });
      }

      if (status !== 'mark') {
        data = $.extend({}, data, { 'rating': star });
      }

      url = REXXAR_API + '/' + type + '/' + id + '/' + status;

      ajaxFn(url, data, function (data) {
        $dialog.addClass(HIDE);

        if (typeof data === 'string') {
          data = $.parseJSON(data);
        }
        $tagComment.html(getMarkHtml(data, attrStatus));

        $self.removeClass(DISABLE);
        $html.removeClass(ISSCROLL);
      }, function () {
        $self.removeClass(DISABLE);
      });
    }).on('click', 'a.btn-cancel', function () {
      $dialog.addClass(HIDE);
      $html.removeClass(ISSCROLL);
    }).on('click', 'div.remark a', function () {
      var $self = $(this);

      if ($self.hasClass('add-tag')) {
        $("#input-tag").removeClass(HIDE).focus();
      } else {
        $self.toggleClass('selected');
      }
    }).on('touchend', 'div.star span', function (e) {
      e.preventDefault();
      var $self = $(this),
          rating = $self.index(),
          curClass = 'rating-star-max-full';

      for (var i = 0; i < 5; i++) {
        if (i <= rating) {
          curClass = 'rating-star-max-full';
        } else {
          curClass = 'rating-star-max-gray';
        }
        $('div.star span', $dialog).eq(i).attr('class', '').addClass(curClass);
      }
    }).on('keyup paste', 'div.douban-dialog textarea', function () {
      var $self = $(this),
          text = $.trim($self.val()),
          strLen = 0,
          textNum = '',
          maxLen = 140,
          curLen = 0,
          $maxLen = $('span.max-length', $dialog);
      for (var i = 0; i < text.length; i++) {
        if (text.charCodeAt(i) < 27 || text.charCodeAt(i) > 126) {
          strLen += 2;
        } else {
          strLen++;
        }
      }
      curLen = Math.ceil(strLen / 2);
      if (curLen <= maxLen) {
        $maxLen.removeClass(ERR);
        $dialog.removeClass(ERR);
        textNum = '';
      } else {
        $maxLen.addClass(ERR);
        $dialog.addClass(ERR);
        $('a.btn-ok', $dialog).addClass(DISABLE);
        textNum = '-';
      }
      $maxLen.text(textNum + Math.ceil(maxLen - curLen));
    }).on('keyup', '#input-tag', function (e) {
      var $self = $(this),
          val = $self.val(),
          iCount = 0;
      if (e.keyCode == '13') {
        isBlur = false;
        checkTag($self);
        return;
      } else {
        isBlur = true;
      }
      if (val) {
        iCount = $self.val().replace(/[^\u0000-\u00ff]/g, "aa");
        $self.attr('size', iCount.length + 2);
      }
    }).on('blur', '#input-tag', function () {
      var $self = $(this);
      if (isBlur) {
        checkTag($self);
      }

      return;
    }).on('focus', '#input-tag', function () {
      $(this).parent().removeClass(ERR);
    });
  }

  function getMarkHtml(interest, attrStatus) {
    var subject = interest.subject,
        rating = interest.rating,
        status = interest.status;
    var type = subject.type;

    var id = subject.id;
    var textObj = STATUS_TEXT_MAP[type];
    var statusText = textObj.status[status] + textObj.thing;
    var curStatus = attrStatus;
    return '\n    <div class="mark-item">\n        ' + ['wish', 'do', 'collect'].map(function (st) {
      if (st === 'do' && ['tv', 'music', 'book'].indexOf(type) < 0) {
        return '';
      }
      return '\n            <a href="javascript:;" rel="nofollow" name="pbtn-' + id + '-' + st + '" class="' + (attrStatus === st ? DISABLE : '') + '">\n                <span>' + ((attrStatus === st ? '已' : '') + STATUS_TEXT_MAP[type].status[STATUS[st]]) + '</span>\n            </a>\n          ';
    }).join('') + '\n    </div>\n    <div class="interest-comment">\n      <h2>\u6211' + statusText + '</h2>\n      ' + (rating ? getRatingDom(rating.value, rating.max) : '') + '\n      <div class="bd">\n          <div class="self-comment">\n              <span>' + htmlEntities(interest.comment) + ' </span>(<a href="javascript:void(0)" class="update-btn" data-status="' + curStatus + '">\u4FEE\u6539</a>&nbsp;&nbsp;<a href="javascript:void(0)" class="delete-btn">\u5220\u9664</a>)\n          </div>\n      </div>\n    </div>';
  }

  function getRatingDom(rating, max, nullRatingReason) {
    var size = arguments.length > 3 && arguments[3] !== undefined ? arguments[3] : 'medium';

    var ratingFloat = parseFloat(rating);
    var ratingNum = ratingFloat / max * 10;

    if (nullRatingReason || isNaN(ratingNum)) {
      return React.createElement(
        'span',
        { className: 'no-rating' },
        nullRatingReason || '暂无评分'
      );
    }

    var starEls = [];
    var stars = Math.round(ratingNum / 2);
    var half = Math.round(ratingNum) % 2;

    var fullCls = 'rating-star-' + size + '-full';
    var halfCls = 'rating-star-' + size + '-half';
    var grayCls = 'rating-star-' + size + '-gray';

    for (var i = 1; i <= 5; i++) {
      var cls = grayCls;
      if (i <= stars) {
        cls = fullCls;
      } else if (i === stars + 1 && half) {
        cls = halfCls;
      }

      starEls.push('<i key=' + i + ' class="rating-star ' + cls + '" />');
    }

    return '<div class="rating">\n          <span class="rating-stars" data-rating="100.0">\n            ' + starEls.join('') + '\n          </span>\n          <span class="score">' + ratingFloat.toFixed(1) + '</span>\n        </div>';
  }

  function goLogin() {
    dui.LoginUtil.checkLogin();
  }

  function ajaxFn(url, data, callback, errback) {
    var data = $.extend({}, data, { ck: CK });
    $.ajax({
      type: 'POST',
      url: url,
      data: data,
      dataType: 'text',
      xhrFields: {
        withCredentials: true
      },
      success: function success(ret) {
        callback(ret);
      },
      error: function error(err) {
        if (errback) {
          errback();
        } else if (err.status === 403) {
          goLogin();
        }
      },
      ajaxError: function ajaxError() {
        errback();
      }
    });
  }

  function getDialog(status) {
    ajaxFn('/j/interest/get_dialog', { ck: CK, subjectId: id, type: type }, function (data) {
      data = $.parseJSON(data);
      $('.bd', $dialog).html(data.html);

      $dialog.removeClass(HIDE).find('a.btn-ok').attr('data-status', status);
      $html.addClass(ISSCROLL);

      if (status != 'wish') {
        $('div.star', $dialog).removeClass(HIDE);
      }
    });
  }

  function getElVal($elements) {
    var results = [];
    for (var i = 0, len = $elements.length; i < len; i++) {
      results.push($elements.eq(i).text());
    }
    return results.join(',');
  }

  function checkTag($el) {
    var tagVal = $el.val(),
        $parent = $el.parent(),
        iCount = 0;

    $('.error', $parent).text('');
    $parent.addClass(ERR);
    $('a.btn-ok', $dialog).addClass(DISABLE);

    if (!tagVal) {
      $el.val('').addClass(HIDE);
      return;
    }
    iCount = tagVal.replace(/[^\u0000-\u00ff]/g, "aa");
    if (iCount.length > 24) {
      alert('标签最长24个字符');
      return;
    } else if (tagVal && !/^[a-zA-Z|\d|\u0391-\uFFE5]*$/.test(tagVal)) {
      alert('标签必须为汉字、字符或数字');
      return;
    }
    for (var i = 0, len = $('.remark a').length; i < len; i++) {
      if (tagVal == $('.remark a').eq(i).text()) {
        alert('已存在这个tag了');
        return;
      }
    }
    $parent.removeClass(ERR);
    $('a.btn-ok', $dialog).removeClass(DISABLE);
    $el.before('<a href="javascript:;" class="selected">' + tagVal + '</a>');
    $el.val('').addClass(HIDE);
  }

  bindEvent();
})(document, Zepto);
          'use strict';

var _slicedToArray = function () { function sliceIterator(arr, i) { var _arr = []; var _n = true; var _d = false; var _e = undefined; try { for (var _i = arr[Symbol.iterator](), _s; !(_n = (_s = _i.next()).done); _n = true) { _arr.push(_s.value); if (i && _arr.length === i) break; } } catch (err) { _d = true; _e = err; } finally { try { if (!_n && _i["return"]) _i["return"](); } finally { if (_d) throw _e; } } return _arr; } return function (arr, i) { if (Array.isArray(arr)) { return arr; } else if (Symbol.iterator in Object(arr)) { return sliceIterator(arr, i); } else { throw new TypeError("Invalid attempt to destructure non-iterable instance"); } }; }();

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _extends = Object.assign || function (target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i]; for (var key in source) { if (Object.prototype.hasOwnProperty.call(source, key)) { target[key] = source[key]; } } } return target; };

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

function _toConsumableArray(arr) { if (Array.isArray(arr)) { for (var i = 0, arr2 = Array(arr.length); i < arr.length; i++) { arr2[i] = arr[i]; } return arr2; } else { return Array.from(arr); } }

var PropTypes = window.React && React.PropTypes;
var REXXAR_API = 'https://m.douban.com/rexxar/api';
var DISPATCH_URL = 'https://www.douban.com/doubanapp/dispatch?from=mdouban&download=0&uri=';
var DOUBAN_URI_SCHEMA = 'douban://douban.com';

function stopEvent(e) {
  e.stopPropagation();
  e.preventDefault();
}

function ajax(config) {
  config.data = config.data || {};
  if (config.withCk !== false) {
    config.data.ck = get_cookie('ck');
  }
  config.data.for_mobile = 1;
  config.error = config.error || function (msg) {
    return dui.toast.warn(msg);
  };
  var _ajaxError = config.ajaxError || function () {
    return dui.toast.networkError();
  };
  config.ajaxError = function (err) {
    err = err || new Error('无网络连接');
    err.isAjaxError = true;
    _ajaxError(err);
  };

  $.ajax($.extend({
    type: config.type || config.method || 'GET',
    url: config.url,
    data: config.data,
    dataType: config.dataType || 'json',
    xhrFields: {
      withCredentials: true
    },
    success: function success(data) {
      if (data.error) {
        if (config.error) {
          config.error(data.error, data.data, data);
        } else {
          alert(data.error);
        }
      } else {
        config.success && config.success(data);
      }
    },
    error: function error(e) {
      console.error(e);
      if (e.status === 0) {
        config.ajaxError && config.ajaxError();
      } else {
        var _data = $.parseJSON(e.response);
        if (_data.code === 1000) {
          _data.localized_message = '没有登录';
          tryLoginMSite();
        }
        config.error && config.error(_data.localized_message, _data);
      }
    },
    complete: function complete(xhr, status) {
      config.complete && config.complete(xhr, status);
    }
  }, config.extra));
}

ajax.all = function (confs, globalConf) {
  var count = 0;
  var globalArgs = {};
  var interceptConfs = function interceptConfs(conf, index) {
    return ['success', 'error', 'ajaxError', 'complete'].reduce(function (callbacks, name) {
      globalArgs[name] = [];
      callbacks[name] = function () {
        var _conf$name;

        for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
          args[_key] = arguments[_key];
        }

        globalArgs[name][index] = args;
        conf[name] && (_conf$name = conf[name]).call.apply(_conf$name, [null].concat(args));
        if (name === 'complete') {
          count++;
          if (count === confs.length) {
            Object.keys(globalConf).forEach(function (gname) {
              var _globalConf$gname;

              return (_globalConf$gname = globalConf[gname]).call.apply(_globalConf$gname, [null].concat(_toConsumableArray(globalArgs[gname])));
            });
          }
        }
      };
      return callbacks;
    }, {});
  };
  confs.forEach(function (conf, i) {
    return ajax(_extends({}, conf, interceptConfs(conf, i)));
  });
};

function autoBind(instance) {
  var unBindMethods = {};
  var filter = function filter(key) {
    return instance[key] instanceof Function && !key.match(/^component|^render$|^constructor$/);
  };

  var methods = [].concat(_toConsumableArray(Object.keys(instance).filter(filter)));
  var parent = instance;

  while (parent = Object.getPrototypeOf(parent)) {
    var ownProperties = Object.getOwnPropertyNames(parent);
    if (ownProperties.indexOf('setState') >= 0) {
      break;
    }
    methods = methods.concat(ownProperties.filter(filter));
  }

  methods.forEach(function (key) {
    unBindMethods[key] = instance[key];
    instance[key] = instance[key].bind(instance);
  });
  return unBindMethods;
}

function flatten(jsonArr) {
  var getId = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : function (entity) {
    return entity.id;
  };

  var flattenedJson = { ids: null, entities: {} };
  flattenedJson.ids = jsonArr.map(function (entity) {
    var id = getId(entity);
    flattenedJson.entities[id] = entity;
    return id;
  });
  return flattenedJson;
}

function getQuery(url, param) {
  if (!url || !/^http/.test(url) && url.indexOf('?') === -1) {
    param = url;
    url = location.href;
  }
  var index = url.indexOf('?');
  var queryObj = {};
  if (index < 0) return param === undefined ? queryObj : '';
  var query = url.substring(index + 1);
  var pairs = query.split('&');
  var pos = void 0,
      key = void 0,
      val = void 0;
  for (var i = 0, len = pairs.length; i < len; i++) {
    pos = pairs[i].indexOf('=');
    if (pos === -1) continue;
    key = pairs[i].substring(0, pos);
    val = pairs[i].substring(pos + 1);
    queryObj[key] = decodeURIComponent(val);
    if (param === key) {
      return queryObj[key];
    }
  }
  return param === undefined ? queryObj : '';
}

function appendQuery(url) {
  var query = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : {};

  var hashRe = /(#.*)$/;
  query = _extends({}, getQuery(url), query);
  var hashMatch = url.match(hashRe);
  var hash = hashMatch ? hashMatch[1] : '';
  url = url.replace(hashRe, '').replace(/\?.*$/, '');
  var qs = Object.keys(query).filter(function (key) {
    return query[key] != null;
  }).map(function (key) {
    url = url.replace(new RegExp(key + '=[^&]+&?', 'g'), '');
    return key + '=' + encodeURIComponent(query[key]);
  }).join('&');
  return url + '?' + qs + hash;
}

function tryLoginMSite() {
  try {
    if (location.hostname.indexOf('dae-pre') >= 0 && get_cookie('ck')) {
      ajax({
        url: 'https://m.douban.com/pwa/offline',
        dataType: 'html',
        extra: {
          timeout: 10 * 1000,
          error: function error(e) {
            return console.error(e);
          }
        }
      });
    }
  } catch (e) {
    window.console && console.warn && console.warn(e);
  }
}

if (document.referrer && document.referrer.indexOf('https://accounts.douban.com/') >= 0) {
  tryLoginMSite();
}
;


var IconBtn = function IconBtn(props) {
  var classNames = ['ic-btn', 'ic-btn-' + props.name, props.active ? 'active' : '', props.left ? 'left' : '', props.right ? 'right' : ''].join(' ');

  return React.createElement(
    'div',
    { className: classNames, onClick: props.onClick },
    props.text !== null ? React.createElement(
      'span',
      { className: 'text' },
      props.text
    ) : null,
    props.children
  );
};

IconBtn.propTypes = {
  name: PropTypes.string.isRequired,
  active: PropTypes.bool,
  left: PropTypes.bool,
  right: PropTypes.bool,
  onClick: PropTypes.func,
  children: PropTypes.node,
  text: PropTypes.oneOfType([PropTypes.string, PropTypes.number])
};

IconBtn.defaultProps = {
  text: null
};

function makeRootDom(Component, config) {

  config = {
    containerId: config && config.containerId || null,
    containerClass: config && config.containerClass || null
  };

  var mountNum = 0;

  var RootDomWrapper = function (_React$Component) {
    _inherits(RootDomWrapper, _React$Component);

    function RootDomWrapper(props) {
      _classCallCheck(this, RootDomWrapper);

      var _this = _possibleConstructorReturn(this, (RootDomWrapper.__proto__ || Object.getPrototypeOf(RootDomWrapper)).call(this, props));

      _this._renderSubtree = _this._renderSubtree.bind(_this);
      _this._copyMethods = _this._copyMethods.bind(_this);
      return _this;
    }

    _createClass(RootDomWrapper, [{
      key: 'componentWillMount',
      value: function componentWillMount() {
        mountNum++;
        this.container = document.createElement('div');
        if (config.containerId) {
          this.container.id = config.containerId + '_' + mountNum;
        }
        if (config.containerClass) {
          this.container.className = config.containerClass;
        }
        document.body.appendChild(this.container);
        this._renderSubtree(this.props, this._copyMethods, false);
      }
    }, {
      key: '_renderSubtree',
      value: function _renderSubtree(props, callback) {
        var async = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : true;

        var that = this;
        function _render() {
          if (!that.container) return;
          ReactDOM.unstable_renderSubtreeIntoContainer(that, React.createElement(
            Component,
            props,
            props.children
          ), that.container, function () {
            if (this && this !== window) {
              that.instance = this;
            }
            callback && callback.call(this);
          });
        }

        if (async) {
          setTimeout(_render);
        } else {
          _render();
        }
      }
    }, {
      key: '_copyMethods',
      value: function _copyMethods() {
        var _this2 = this;

        var instance = this.instance;
        if (!instance) return;[].concat(_toConsumableArray(Object.getOwnPropertyNames(Component.prototype)), _toConsumableArray(Object.keys(instance))).forEach(function (key) {
          if (instance[key] instanceof Function && !(key in _this2) && !key.match(/^component/) && !key.match(/^_/)) {
            var originalMethod = instance[key];
            _this2[key] = function () {
              var args = arguments;
              this._renderSubtree(this.props, function () {
                originalMethod.apply(instance, [].slice.call(args));
              });
            };
          }
        });
      }
    }, {
      key: 'componentWillReceiveProps',
      value: function componentWillReceiveProps(nextProps) {
        this._renderSubtree(nextProps);
      }
    }, {
      key: 'render',
      value: function render() {
        return null;
      }
    }, {
      key: 'componentWillUnmount',
      value: function componentWillUnmount() {
        this._clearContainer();
      }
    }, {
      key: '_clearContainer',
      value: function _clearContainer() {
        ReactDOM.unmountComponentAtNode(this.container);
        document.body.removeChild(this.container);
        this.instance = null;
        this.container = null;
      }
    }]);

    return RootDomWrapper;
  }(React.Component);

  var originComponentName = Component.displayName || Component.name || 'Component';

  RootDomWrapper.displayName = 'RootDomWrapper(' + originComponentName + ')';
  return RootDomWrapper;
}
;
var popBoundType = PropTypes.shape({
  x: PropTypes.number,
  y: PropTypes.number,
  width: PropTypes.number,
  height: PropTypes.number
});

var defaultBound = {
  x: 0, y: 0,
  width: window.innerWidth,
  height: window.innerHeight - 53
};

var PopMenuItem = function PopMenuItem(props) {
  return React.createElement(
    'div',
    {
      onClick: props.onClick,
      className: 'item'
    },
    props.text
  );
};

PopMenuItem.propTypes = {
  onChange: PropTypes.func,
  onClick: PropTypes.func,
  defaultValue: PropTypes.string
};
PopMenuItem.defaultProps = {
  defaultValue: ''
};

var PopMenu = function (_React$Component2) {
  _inherits(PopMenu, _React$Component2);

  function PopMenu() {
    _classCallCheck(this, PopMenu);

    return _possibleConstructorReturn(this, (PopMenu.__proto__ || Object.getPrototypeOf(PopMenu)).apply(this, arguments));
  }

  _createClass(PopMenu, [{
    key: 'componentDidUpdate',
    value: function componentDidUpdate(prevProps, prevState) {
      this.updateWidth();
      if (this.props.triggerPos !== prevProps.triggerPos) {
        this.updatePosition(this.props.triggerPos);
      }
    }
  }, {
    key: 'render',
    value: function render() {
      var classNames = ['pop-menu-wrapper', this.props.show ? 'show' : ''].join(' ');
      return React.createElement(
        'div',
        {
          className: classNames,
          onClick: this.props.onRequestHide,
          onTouchMove: this.props.onRequestHide
        },
        React.createElement(
          'div',
          {
            className: 'pop-menu',
            ref: 'popMenuDiv',
            onClick: stopEvent
          },
          this.props.children
        )
      );
    }
  }, {
    key: 'updatePosition',
    value: function updatePosition(triggerPos) {
      var popMenuDom = ReactDOM.findDOMNode(this.refs.popMenuDiv);
      var bound = this.props.bound;
      var minX = bound.x,
          minY = bound.y,
          maxWidth = bound.width,
          maxHeight = bound.height,
          menuWidth = popMenuDom.offsetWidth,
          menuHeight = popMenuDom.offsetHeight,
          popPos = { x: 0, y: 0 };
      popPos.x = Math.max(minX, triggerPos.x + 10);
      popPos.x = Math.min(popPos.x, maxWidth - menuWidth - 10);
      popPos.y = Math.max(minY, triggerPos.y - menuHeight - 20);
      popPos.y = Math.min(popPos.y, maxHeight - menuHeight - 10);

      popMenuDom.style.left = popPos.x + 'px';
      popMenuDom.style.top = popPos.y + 'px';
    }
  }, {
    key: 'updateWidth',
    value: function updateWidth() {
      var popMenuDom = ReactDOM.findDOMNode(this.refs.popMenuDiv);
      var sumWidth = [].slice.call(popMenuDom.children).reduce(function (sum, el) {
        return sum + el.clientWidth;
      }, 0);
      popMenuDom.style.width = sumWidth + 2 + 'px';
    }
  }]);

  return PopMenu;
}(React.Component);

PopMenu.propTypes = {
  children: PropTypes.node,
  bound: popBoundType,
  onRequestHide: PropTypes.func,
  show: PropTypes.bool,
  triggerPos: PropTypes.shape({
    x: PropTypes.number.isRequired,
    y: PropTypes.number.isRequired
  })
};

PopMenu.defaultProps = {
  bound: defaultBound,
  show: false
};

PopMenu = makeRootDom(PopMenu, { containerId: 'pop-menu' });
;(function (React, ReactDOM, $) {

  var EVENT_TYPE = {
    'DEL_PIC_CUSTOM': 'DEL_PIC_CUSTOM',
    'DEL_PIC_DETAULT_SUCCESS': 'DEL_PIC_DETAULT_SUCCESS',
    'DEL_PIC_DETAULT_ERROR': 'DEL_PIC_DETAULT_ERROR',
    'DEL_PIC_DETAULT_COMPLETE': 'DEL_PIC_DETAULT_COMPLETE',

    'REPORT_CUSTOM': 'REPORT_CUSTOM',
    'REPORT_DEFAULT_SUCCESS': 'REPORT_DEFAULT_SUCCESS',
    'REPORT_DEFAULT_ERROR': 'REPORT_DEFAULT_ERROR',
    'REPORT_DEFAULT_COMPLETE': 'REPORT_DEFAULT_COMPLETE',

    'DEL_COMMENT_CUSTOM': 'DEL_COMMENT_CUSTOM',
    'DEL_COMMENT_DEFAULT_SUCCESS': 'DEL_COMMENT_DEFAULT_SUCCESS',
    'DEL_COMMENT_DEFAULT_ERROR': 'DEL_COMMENT_DEFAULT_ERROR',
    'DEL_COMMENT_DEFAULT_COMPLETE': 'DEL_COMMENT_DEFAULT_COMPLETE'
  };

  var ReportMenu = React.createClass({
    displayName: 'ReportMenu',


    componentDidMount: function componentDidMount() {
      var reportContainer = ReactDOM.findDOMNode(this).parentElement;
      $(reportContainer).on('click', function (e) {
        if (e.target.nodeName === 'DIV') this.remove(reportContainer);
      }.bind(this));
    },

    remove: function remove(reportContainer) {
      reportContainer = reportContainer || ReactDOM.findDOMNode(this).parentElement;
      ReactDOM.unmountComponentAtNode(reportContainer);
      $(reportContainer).off().remove();
    },

    handleClick: function handleClick(reason) {
      var $container = this.props.container;

      $.ajax({
        url: '/j/report',
        type: 'post',
        dataType: 'json',
        data: {
          reason: reason,
          url: $container.data('report-url'),
          ck: get_cookie('ck')
        },
        success: function success(res) {
          if (res.r === 0) {
            dui && dui.toast.success('举报成功');
          } else {
            dui && dui.toast.error('举报失败');
          }
          MenuRender.trigger(EVENT_TYPE.REPORT_DEFAULT_SUCCESS, res);
        },
        error: function error(xhr, errorType, _error) {
          dui && dui.toast.error('举报失败');
          MenuRender.trigger(EVENT_TYPE.REPORT_DEFAULT_ERROR, [xhr, errorType, _error]);
        },
        complete: function (xhr, status) {
          this.remove();
          MenuRender.trigger(EVENT_TYPE.REPORT_DEFAULT_COMPLETE, status);
        }.bind(this)
      });
    },

    render: function render() {
      return React.createElement(
        'ul',
        null,
        React.createElement(
          'li',
          { onClick: this.handleClick.bind(this, 0) },
          '\u5E7F\u544A\u7B49\u5783\u573E\u4FE1\u606F'
        ),
        React.createElement(
          'li',
          { onClick: this.handleClick.bind(this, 1) },
          '\u8272\u60C5\u6216\u4F4E\u4FD7\u5185\u5BB9'
        ),
        React.createElement(
          'li',
          { onClick: this.handleClick.bind(this, 2) },
          '\u6FC0\u8FDB\u65F6\u653F\u6216\u610F\u8BC6\u5F62\u6001\u8BDD\u9898'
        ),
        React.createElement(
          'li',
          { onClick: this.handleClick.bind(this, 3) },
          '\u5176\u4ED6\u539F\u56E0'
        )
      );
    }
  });

  var MenuItem = React.createClass({
    displayName: 'MenuItem',


    report: function report($container) {
      $('body').append('<div id="report-box"></div>');

      ReactDOM.render(React.createElement(ReportMenu, { container: $container }), $('#report-box')[0]);
    },

    del: function del($container) {
      $.ajax({
        url: '/j/remove_comment/',
        type: 'post',
        dataType: 'json',
        data: {
          tid: $container.data('tid'),
          cid: $container.data('cid'),
          ck: get_cookie('ck')
        },
        success: function success(res) {
          if (res.r === 0) {
            dui && dui.toast.success('删除成功');
          } else {
            dui && dui.toast.error('删除失败');
          }
          ReactDOM.unmountComponentAtNode($container[0]);
          MenuRender.trigger(EVENT_TYPE.DEL_COMMENT_DEFAULT_SUCCESS, [res, $container]);
        },
        error: function error(xhr, errorType, _error2) {
          dui && dui.toast.error('删除失败');
          MenuRender.trigger(EVENT_TYPE.DEL_COMMENT_DEFAULT_ERROR, [xhr, errorType, _error2]);
        },
        complete: function complete(xhr, status) {
          MenuRender.trigger(EVENT_TYPE.DEL_COMMENT_DEFAULT_COMPLETE, status);
        }
      });
    },

    del_pic: function del_pic($container) {
      $.ajax({
        url: 'https://m.douban.com/rexxar/api/v2/photo/' + $container.data('tid') + '/delete',
        type: 'POST',
        dataType: 'json',
        data: { ck: get_cookie('ck') },
        xhrFields: { withCredentials: true },
        success: function success() {
          MenuRender.trigger(EVENT_TYPE.DEL_PIC_DETAULT_SUCCESS, $container);
          dui && dui.toast.success('删除成功');
        },
        error: function error(xhr, errorType, _error3) {
          MenuRender.trigger(EVENT_TYPE.DEL_PIC_DETAULT_ERROR, $container);
          dui && dui.toast.error('删除失败');
        },
        complete: function complete() {
          MenuRender.trigger(EVENT_TYPE.DEL_PIC_DETAULT_COMPLETE, $container);
        }
      });
    },

    handleClick: function handleClick(action, $container) {
      if (!$container.data('islogin')) {
        dui.LoginUtil.checkLogin();
        return;
      }
      switch (action) {
        case 'report':
          if ($container.data('report-default') === false) {
            MenuRender.trigger(EVENT_TYPE.REPORT_CUSTOM, $container);
          } else {
            this.report($container);
          }
          break;

        case 'delete':
          if ($container.data('delete-default') === false) {
            MenuRender.trigger(EVENT_TYPE.DEL_COMMENT_CUSTOM, $container);
          } else {
            this.del($container);
          }
          break;

        case 'delete_pic':
          if ($container.data('delete-pic-default') === false) {
            MenuRender.trigger(EVENT_TYPE.DEL_PIC_CUSTOM, $container);
          } else {
            this.del_pic($container);
          }
          break;
      }
    },

    render: function render() {
      var handleClick = this.handleClick.bind(this, this.props.action, this.props.container);
      return React.createElement(
        'li',
        { className: 'more-menu-item', onClick: handleClick },
        this.props.text
      );
    }
  });

  var MainMenu = React.createClass({
    displayName: 'MainMenu',

    render: function render() {
      var $container = this.props.container;

      var can_report = $container.data('can-report');
      var can_delete = $container.data('can-delete');
      var can_delete_pic = $container.data('can-delete-pic');

      return React.createElement(
        'ul',
        { className: 'more-menu' },
        can_delete_pic ? React.createElement(MenuItem, { text: '\u5220\u9664\u56FE\u7247', action: 'delete_pic', container: $container }) : null,
        can_delete ? React.createElement(MenuItem, { text: '\u5220\u9664', action: 'delete', container: $container }) : null,
        can_report ? React.createElement(MenuItem, { text: '\u4E3E\u62A5', action: 'report', container: $container }) : null
      );
    }
  });

  window.MenuRender = function (selector) {
    $(selector).each(function () {
      ReactDOM.render(React.createElement(MainMenu, { container: $(this) }), this);
    });
    return MenuRender;
  };

  MenuRender.eventCenter = {};

  MenuRender.on = function (eventNames, func) {
    if (!Array.isArray(eventNames)) eventNames = [eventNames];

    eventNames.forEach(function (name) {
      MenuRender.eventCenter[name] = MenuRender.eventCenter[name] || [];
      MenuRender.eventCenter[name].push(func);
    });

    return MenuRender;
  };

  MenuRender.trigger = function (eventName, args) {
    var e = MenuRender.eventCenter[eventName];
    if (!e) return;
    MenuRender.eventCenter[eventName].forEach(function (func) {
      func[Array.isArray(args) ? 'apply' : 'call'](null, args);
    });

    return MenuRender;
  };

  MenuRender.EVENT_TYPE = EVENT_TYPE;
  MenuRender.version = '0.2';
})(React, ReactDOM, Zepto);

var Dialog = function Dialog(_ref) {
  var children = _ref.children,
      top = _ref.top,
      show = _ref.show,
      onRequestHide = _ref.onRequestHide,
      moreClass = _ref.moreClass,
      _ref$className = _ref.className,
      className = _ref$className === undefined ? moreClass : _ref$className,
      moreWrapperClass = _ref.moreWrapperClass,
      stopScroll = _ref.stopScroll,
      transparent = _ref.transparent;

  var posTop = {
    top: typeof top === 'number' ? top + 'px' : top
  };
  var classNames = ['dialog-container', show ? 'show' : '', transparent ? 'transparent' : '', className].join(' ');
  return React.createElement(
    'div',
    {
      className: classNames,
      onClick: function onClick(e) {
        if (e.target.className.indexOf('dialog-overlay') >= 0) {
          onRequestHide && onRequestHide();
        }
      }
    },
    React.createElement('div', {
      className: 'dialog-overlay',
      onTouchMove: stopScroll ? stopEvent : null
    }),
    React.createElement(
      'div',
      {
        style: posTop,
        className: 'dialog-wrapper'
      },
      React.createElement(
        'div',
        { className: 'dialog' },
        children
      )
    )
  );
};

Dialog.propTypes = {
  children: PropTypes.node,
  top: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
  show: PropTypes.bool,
  onRequestHide: PropTypes.func,
  moreClass: PropTypes.string,
  className: PropTypes.string,
  stopScroll: PropTypes.bool,
  transparent: PropTypes.bool
};

Dialog.defaultProps = {
  show: false,
  moreClass: '',
  stopScroll: true,
  transparent: false
};

Dialog = makeRootDom(Dialog, { containerId: 'dialog' });

var ReportDialog = function (_React$Component3) {
  _inherits(ReportDialog, _React$Component3);

  function ReportDialog() {
    _classCallCheck(this, ReportDialog);

    return _possibleConstructorReturn(this, (ReportDialog.__proto__ || Object.getPrototypeOf(ReportDialog)).apply(this, arguments));
  }

  _createClass(ReportDialog, [{
    key: 'handleItemClick',
    value: function handleItemClick(value) {
      var _this5 = this;

      ajax({
        type: 'POST',
        url: this.props.reportUrl,
        data: this.props.reportData(value, this.props),
        success: function success(data) {
          dui.toast.success('举报成功');
          _this5.onSuccess && _this5.onSuccess(data);
          _this5.props.onRequestHide && _this5.props.onRequestHide();
        },
        error: function error(_error4) {
          _error4 = _error4 || '举报失败';
          dui.toast.warn(_error4);
          _this5.onError && _this5.onError(_error4);
        }
      });
    }
  }, {
    key: 'render',
    value: function render() {
      var _this6 = this;

      var _props = this.props,
          show = _props.show,
          onRequestHide = _props.onRequestHide,
          reasonList = _props.reasonList;

      return React.createElement(
        Dialog,
        {
          show: show,
          onRequestHide: onRequestHide,
          moreClass: 'report-dialog'
        },
        reasonList.map(function (item) {
          return React.createElement(
            'div',
            {
              key: item.value,
              className: 'reason-item',
              onClick: _this6.handleItemClick.bind(_this6, item.value)
            },
            item.label
          );
        })
      );
    }
  }]);

  return ReportDialog;
}(React.Component);

ReportDialog.propTypes = {
  show: PropTypes.bool,
  url: PropTypes.string,
  reportData: PropTypes.func,
  reportUrl: PropTypes.string,
  reasonList: PropTypes.arrayOf(PropTypes.shape({
    value: PropTypes.string.isRequired,
    label: PropTypes.string.isRequired
  })),
  onRequestHide: PropTypes.func,
  onSuccess: PropTypes.func,
  onError: PropTypes.func,
  onAjaxError: PropTypes.func
};

ReportDialog.defaultProps = {
  show: false,
  url: '',
  reportData: function reportData(reason, props) {
    return { reason: reason, url: props.url };
  },
  reportUrl: '/j/report',
  reasonList: [{ value: '0', label: '广告或垃圾信息' }, { value: '1', label: '色情或低俗内容' }, { value: '2', label: '激进时政或意识形态话题' }, { value: '3', label: '其他原因' }]
};

(function () {

  function icHot() {
    var _ref2 = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {},
        _ref2$className = _ref2.className,
        className = _ref2$className === undefined ? '' : _ref2$className,
        _ref2$style = _ref2.style,
        style = _ref2$style === undefined ? '' : _ref2$style,
        _ref2$color = _ref2.color,
        color = _ref2$color === undefined ? '#42BD56' : _ref2$color;

    return '\n  <svg width="13px" height="17px" class="' + className + '" style="' + style + '" viewBox="0 0 25 34" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">\n      <!-- Generator: Sketch 46.2 (44496) - http://www.bohemiancoding.com/sketch -->\n      <desc>Created with Sketch.</desc>\n      <defs></defs>\n      <g id="Page-1" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">\n          <g id="\u5438\u5E95\u6309\u94AE\uFF08\u67E5\u770B\u5F71\u8BC4\uFF09" transform="translate(-185.000000, -1269.000000)" stroke="' + color + '" stroke-width="2">\n              <g id="Group-2" transform="translate(186.000000, 1266.000000)">\n                  <path d="M-1.77635684e-15,24.995212 C-1.77635684e-15,30.3710132 4.88378906,34.5263672 9.07641602,35 C7.89538574,32.045166 9.29394531,28.5505371 11.3920645,26.869049 C13.7368164,28.8355713 14.8319092,31.9631348 13.6361084,35 C18.7408447,34.3273926 22.784129,29.4827361 22.784129,23.8055105 C22.784129,18.6281924 20.1739358,13.7479461 15.249743,10.7129177 C16.4201963,13.9706955 14.8638233,16.94718 13.3500892,17.9725671 C13.4398152,13.8007225 11.9260811,7.61085781 7.12004032,5 C7.89187981,13.986474 -1.77635684e-15,17.0799373 -1.77635684e-15,24.995212 Z" id="Fill-1"></path>\n              </g>\n          </g>\n      </g>\n  </svg>';
  }
  ;
  function icComment() {
    var _ref3 = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {},
        _ref3$className = _ref3.className,
        className = _ref3$className === undefined ? '' : _ref3$className,
        _ref3$style = _ref3.style,
        style = _ref3$style === undefined ? '' : _ref3$style,
        _ref3$color = _ref3.color,
        color = _ref3$color === undefined ? '#42BD56' : _ref3$color;

    return '\n  <svg class="' + className + '" style="' + style + '" width="19px" height="15px" viewBox="0 0 38 31" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">\n      <title>Group 24</title>\n      <desc>Created with Sketch.</desc>\n      <defs></defs>\n      <g id="Page-1" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">\n          <g id="\u5438\u5E95\u6309\u94AE\uFF08\u65E5\u8BB0\u56DE\u5E94\u3001\u5C0F\u7EC4\u56DE\u5E94\u3001\u5F71\u8BC4\u56DE\u5E94\uFF09\uFF09" transform="translate(-178.000000, -1183.000000)" stroke="' + color + '" stroke-width="2">\n              <g id="Group" transform="translate(178.000000, 1176.000000)">\n                  <g id="Group-24" transform="translate(0.000000, 7.000000)">\n                      <path d="M16.7700913,20.2581588 L27.249849,11.0079963 L27.249849,5.22657899 C27.249849,2.89607791 25.3582028,1 23.0309625,1 L8.13287555,1 C5.80407889,1 3.91398905,2.89191497 3.91398905,5.22499682 L3.91398905,17.2839062 L3.851937,17.4517425 L1.6793174,23.3277246 C1.56220701,23.6624334 1.57131814,23.7810996 1.52298344,23.7348835 C1.47350666,23.6875755 1.58894654,23.6910894 1.89907115,23.5657376 L9.73536752,20.2581589 L16.7700913,20.2581588 Z" id="Rectangle-11"></path>\n                      <path d="M19.2028041,23.5207129 L19.2028041,14.482435 C19.2028041,12.8206394 20.5391031,11.4744572 22.1986296,11.475661 L33.9857706,11.4842111 C35.6403193,11.4854112 36.9815961,12.8282583 36.9815961,14.4802809 L36.9815961,23.4648535 C36.9815961,25.118337 35.6378461,26.4587503 33.990253,26.4587503 L24.084184,26.4587505 L17.672076,29.3142765 C17.167891,29.538807 16.9232966,29.3043906 17.1250053,28.7926055 L19.2028041,23.5207129 Z" id="Rectangle-11" fill="#FFFFFF" transform="translate(27.017953, 20.437597) scale(-1, 1) translate(-27.017953, -20.437597) "></path>\n                  </g>\n              </g>\n          </g>\n      </g>\n  </svg>\n  ';
  }
  ;

  function redirectToOpenApp() {
    var uri = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : 'douban://douban.com/';

    var _ref4 = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : {},
        _ref4$from = _ref4.from,
        from = _ref4$from === undefined ? 'mdouban' : _ref4$from,
        _ref4$source = _ref4.source,
        source = _ref4$source === undefined ? '' : _ref4$source,
        _ref4$promo_type = _ref4.promo_type,
        promo_type = _ref4$promo_type === undefined ? '' : _ref4$promo_type,
        _ref4$promo_copy_no = _ref4.promo_copy_no,
        promo_copy_no = _ref4$promo_copy_no === undefined ? '1' : _ref4$promo_copy_no,
        _ref4$is_download_url = _ref4.is_download_url,
        is_download_url = _ref4$is_download_url === undefined ? false : _ref4$is_download_url,
        _ref4$direct_dl = _ref4.direct_dl,
        direct_dl = _ref4$direct_dl === undefined ? '1' : _ref4$direct_dl,
        _ref4$returnUrl = _ref4.returnUrl,
        returnUrl = _ref4$returnUrl === undefined ? false : _ref4$returnUrl,
        _ref4$fallback = _ref4.fallback,
        fallback = _ref4$fallback === undefined ? '' : _ref4$fallback,
        _ref4$android_dispatc = _ref4.android_dispatch,
        android_dispatch = _ref4$android_dispatc === undefined ? false : _ref4$android_dispatc;

    var redirUrl = void 0;
    if (is_download_url) {
      redirUrl = 'https://www.douban.com/doubanapp/app?from=' + from + '&page=' + promo_type + '&model=B&copy=' + promo_copy_no + '&channel=' + source + '&direct_dl=' + direct_dl;
    } else {
      var lowerUA = navigator.userAgent.toLowerCase();
      var isIOS = /(iphone|ipad|ipod|ios)/i.test(lowerUA);
      var isAndroid = lowerUA.match(/(android);?[\s\/]+([\d.]+)?/);
      var getIOSVersion = function getIOSVersion() {
        var m = lowerUA.match(/os ([0-9]+)_/);
        return m ? +m[1] : 0;
      };
      redirUrl = 'https://www.douban.com/doubanapp/card/get_app?client_uri=' + uri + '&from=' + from + '&page=' + promo_type + '&model=B&copy=' + promo_copy_no + '&channel=' + source + '&fallback=' + fallback;

      if (isAndroid && android_dispatch) {
        var dispatch_url = escape('https://www.douban.com/doubanapp/dispatch?uri=' + (uri.replace('douban://douban.com', '') || '/') + '&fallback=' + escape(fallback));
        var webview_uri = 'douban://douban.com/webview?url=' + escape(dispatch_url);
        redirUrl = 'https://www.douban.com/doubanapp/card/get_app?client_uri=' + escape(webview_uri);
      }

      if (isIOS && getIOSVersion() >= 9) {
        redirUrl = '' + DISPATCH_URL.replace('download=0', 'download=1') + (uri.replace('douban://douban.com', '') || '/') + '&from=' + from + '&page=' + promo_type + '&model=B&copy=' + promo_copy_no + '&channel=' + source + '&fallback=' + fallback;
      }
    }

    if (returnUrl) {
      return redirUrl;
    }

    location.href = redirUrl;
  }
  ;

  var className = 'open-in-app-fixed-bottom';
  var enabledUrls = {
    movie_reviews: '^/movie/subject/(\\d+)/reviews/?$',
    movie_review_comments: '^/movie/review/(\\d+)/comments/?$',
    group: '^/group/topic/(\\d+)/comments/?$',
    note: '^/note/(\\d+)/comments/?$'
  };
  var type = void 0;
  var id = void 0;
  function isEnabled(url) {
    var match = void 0;
    for (var _type in enabledUrls) {
      if (match = location.pathname.match(enabledUrls[_type])) {
        type = _type;
        id = match[1];
        if (document.querySelector('.' + className)) {
          return false;
        }
        return true;
      }
    }
    return false;
  }

  function removeButton() {
    var commentBtn = document.querySelector('.comment-button');
    if (commentBtn) {
      if (!dui.LoginUtil.isLogin()) {
        commentBtn.style.display = 'none';
        return [true, false];
      } else {
        return [false, false];
      }
    }
    return [true, true];
  }

  function addPromoButton() {
    var $link = document.createElement('a');
    $link.className = className;
    $link.innerHTML = type === 'movie_reviews' ? icHot({ className: 'icon' }) + '<span>\u6253\u5F00\u8C46\u74E3\uFF0C\u67E5\u770B\u70ED\u95E8\u5F71\u8BC4</span>' : icComment({ className: 'icon' }) + '<span>\u6253\u5F00\u8C46\u74E3\uFF0C\u8BF4\u8BF4\u4F60\u7684\u89C2\u70B9</span>';

    function promoUrl(uri, source) {
      var type = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : '';

      if (!uri.match(/^douban:\/\//) || !uri.match(/^http/)) {
        uri = '' + DOUBAN_URI_SCHEMA + uri;
      }
      return redirectToOpenApp(uri, {
        source: source,
        promo_type: type,
        returnUrl: true
      });
    }

    switch (type) {
      case 'movie_reviews':
        $link.href = promoUrl('/movie/' + id + '/reviews', 'card_movie_review', 'movie');
        break;
      case 'movie_review_comments':
        $link.href = promoUrl('/review/' + id + '/comments', 'card_movie_comments', 'movie');
        break;
      case 'note':
        $link.href = promoUrl('/note/' + id + '/comments', 'card_note_comments', 'note');
        break;
      case 'group':
        $link.href = promoUrl('/group/topic/' + id + '/comments', 'card_group_comments', 'topic');
        break;
      default:
        console.error(new Error('Unknown type for promo button(fixed bottom) :' + type));
    }

    document.body.appendChild($link);
  }

  function main() {
    if (!isEnabled()) {
      return;
    }

    var _removeButton = removeButton(),
        _removeButton2 = _slicedToArray(_removeButton, 2),
        needAddPromoButton = _removeButton2[0],
        needAddBodyPadding = _removeButton2[1];

    if (needAddPromoButton) {
      addPromoButton();
    }
    if (needAddBodyPadding) {
      var oldBottom = parseInt(document.body.style.paddingBottom || 0) || 0;
      document.body.style.paddingBottom = oldBottom + 50 + 'px';
    }
  }

  setTimeout(main, 10);
})();

var CommentList = function (_React$Component4) {
  _inherits(CommentList, _React$Component4);

  function CommentList(props) {
    _classCallCheck(this, CommentList);

    var _this7 = _possibleConstructorReturn(this, (CommentList.__proto__ || Object.getPrototypeOf(CommentList)).call(this, props));

    _this7.state = _defineProperty({
      subject: props.subject,
      commentList: props.commentList,
      hasMore: props.hasMore,
      hasAd: props.hasAd,
      ref_comment: null,
      popMenuIsShow: false,
      currentComment: null,
      reportUrl: null,
      reportDialogIsShow: false,
      inputDialogIsShow: false
    }, 'currentComment', null);

    autoBind(_this7);
    return _this7;
  }

  _createClass(CommentList, [{
    key: 'isSelf',
    value: function isSelf(user) {
      var self = dui.LoginUtil.getUserInfo();
      if (self && user.id === self.id) {
        return true;
      } else {
        return false;
      }
    }
  }, {
    key: 'showPopMenu',
    value: function showPopMenu(comment, e) {
      this.setState({
        currentComment: comment,
        popMenuIsShow: true,
        popMenuTriggerPos: {
          x: e.clientX,
          y: e.clientY
        }
      });
    }
  }, {
    key: 'hidePopMenu',
    value: function hidePopMenu() {
      this.setState({
        popMenuIsShow: false
      });
    }
  }, {
    key: 'handleUsefull',
    value: function handleUsefull(comment, sid) {
      var _this8 = this;

      var toast = dui.toast;

      if (!get_cookie('ck')) {
        dui.LoginUtil.checkLogin();
      }

      if (this.isSelf(comment.user)) {
        toast.success('不能给自己投票');
        return;
      }

      if (comment.is_voted) {
        toast.success('你已经投过票了');
        return;
      }
      ajax({
        url: REXXAR_API + '/v2/' + this.state.subject.type + '/' + sid + '/vote_interest',
        type: 'POST',
        data: {
          ck: get_cookie('ck'),
          interest_id: comment.id
        },
        success: function success(data) {
          var commentList = _this8.state.commentList;
          var newComment = null;
          for (var i = 0, len = commentList.length; i < len; i++) {
            newComment = commentList[i];
            if (newComment['id'] == comment.id) {
              newComment.vote_count = data.vote_count;
              newComment.is_voted = data.is_voted;
              _this8.setState({
                commentList: commentList
              });
            }
          }
        }
      });
    }
  }, {
    key: 'renderIcons',
    value: function renderIcons(comment, sid) {
      var icons = [React.createElement(IconBtn, { name: 'like', key: 'like', text: comment.vote_count, active: comment.is_voted, left: true, onClick: this.handleUsefull.bind(this, comment, sid) })];
      if (!this.isSelf(comment.user)) {
        icons.push(React.createElement(IconBtn, { name: 'more', key: 'more', right: true, onClick: this.showPopMenu.bind(this, comment) }));
      }
      return icons;
    }
  }, {
    key: 'showReportDialog',
    value: function showReportDialog() {
      if (!dui.LoginUtil.checkLogin()) {
        return;
      }
      var comment = this.state.currentComment;

      this.setState({
        reportDialogIsShow: true,
        reportUrl: comment.uri
      });
    }
  }, {
    key: 'hideReportDialog',
    value: function hideReportDialog() {
      this.setState({
        reportDialogIsShow: false,
        popMenuIsShow: false
      });
    }
  }, {
    key: 'renderPopMenu',
    value: function renderPopMenu() {
      var _state = this.state,
          currentComment = _state.currentComment,
          popMenuIsShow = _state.popMenuIsShow,
          popMenuTriggerPos = _state.popMenuTriggerPos;

      return React.createElement(
        PopMenu,
        {
          show: popMenuIsShow,
          onRequestHide: this.hidePopMenu,
          triggerPos: popMenuTriggerPos
        },
        React.createElement(PopMenuItem, { text: '\u4E3E\u62A5',
          onClick: this.showReportDialog })
      );
    }
  }, {
    key: 'renderReportDialog',
    value: function renderReportDialog() {
      var _state2 = this.state,
          reportDialogIsShow = _state2.reportDialogIsShow,
          reportUrl = _state2.reportUrl;

      return React.createElement(ReportDialog, {
        show: reportDialogIsShow,
        onRequestHide: this.hideReportDialog,
        reportUrl: REXXAR_API + '/v2/report',
        reportData: function reportData(reason) {
          return { reason: reason, uri: reportUrl };
        } });
    }
  }, {
    key: 'createMarkup',
    value: function createMarkup(tpl, data) {
      return { __html: tpl.replace(/{([^{|^}]+)}/g, function (_, key) {
          return data[key] || '';
        }) };
    }
  }, {
    key: 'renderAd',
    value: function renderAd(index) {
      if (this.state.hasAd) {
        if (index == 5) {
          return React.createElement('div', { className: 'center', dangerouslySetInnerHTML: $('div.ad-1').html });
        } else if (index == 13) {
          return React.createElement('div', { className: 'center', dangerouslySetInnerHTML: $('div.ad-1').html });
        }
      }
    }
  }, {
    key: 'renderComment',
    value: function renderComment(c, index) {
      var subject = this.state.subject;

      if ($('#commentTpl').size() > 0) {
        var tpl = $('#commentTpl').html();
        return React.createElement(
          'li',
          { className: this.props.liClass, key: c.id },
          React.createElement('div', { dangerouslySetInnerHTML: this.createMarkup(tpl, c) }),
          this.renderIcons(c, this.state.subject.sid)
        );
      } else {
        var user = c.user || {};
        var rating = c.rating && c.rating.value || 0;
        return React.createElement(
          'li',
          { className: this.props.liClass, key: c.id },
          React.createElement(
            'div',
            { className: 'desc' },
            React.createElement(
              'a',
              { href: '/people/' + user.id + '/' },
              React.createElement('img', { src: user.avatar || '', alt: user.name || '' })
            ),
            React.createElement(
              'div',
              { className: 'user-info' },
              React.createElement(
                'strong',
                null,
                user.name || ''
              ),
              !subject.hasNotShow && React.createElement(
                'span',
                { className: 'rating-stars', 'data-rating': rating },
                this.renderStar(rating)
              ),
              React.createElement(
                'div',
                { className: 'date' },
                c.create_time
              )
            )
          ),
          React.createElement(
            'p',
            null,
            c.comment
          ),
          React.createElement(
            'div',
            { className: 'btn-info' },
            this.renderIcons(c, this.state.subject.sid)
          ),
          this.renderAd(index)
        );
      }
    }
  }, {
    key: 'renderStar',
    value: function renderStar(ratingNum) {
      var starEls = [];
      var stars = Math.round(ratingNum);


      var fullCls = 'rating-star-medium-full';

      var grayCls = 'rating-star-medium-gray';

      for (var i = 1; i <= 5; i++) {
        var cls = grayCls;
        if (i <= stars) {
          cls = fullCls;
        }

        starEls.push(React.createElement('span', { key: i, className: 'rating-star ' + cls }));
      }
      return starEls;
    }
  }, {
    key: 'renderMore',
    value: function renderMore() {
      if (this.state.hasMore) {
        var _subject = this.state.subject;
        var linkType = _subject.type;
        if (linkType === 'tv') {
          linkType = 'movie';
        } else if (linkType === 'app') {
          linkType = 'mobileapp';
        }
        var moreUrl = '/' + linkType + '/subject/' + _subject.sid + '/comments?from=subject';
        return React.createElement(
          'li',
          { className: 'go-comment-list' },
          React.createElement(
            'a',
            { href: moreUrl },
            '\u67E5\u770B\u5168\u90E8\u77ED\u8BC4'
          )
        );
      }
    }
  }, {
    key: 'render',
    value: function render() {
      var _this9 = this;

      var _props2 = this.props,
          commentList = _props2.commentList,
          subject = _props2.subject;


      return React.createElement(
        'ul',
        { className: this.props.ulClass },
        this.state.commentList.map(function (c, index) {
          return _this9.renderComment(c);
        }),
        this.renderMore(),
        this.renderPopMenu(),
        this.renderReportDialog()
      );
    }
  }]);

  return CommentList;
}(React.Component);

CommentList.defaultProps = {
  commentList: [],
  hasMore: false,
  hasAd: false,
  ulClass: 'list comment-list',
  liClass: ''
};
var subject = TalionData.subject || {},
    SID = subject.sid,
    CID = subject.cid,
    TYPE = subject.type,
    SORT = getQuery('sort') === 'time' ? 'latest' : 'hot',
    data = {
  count: TalionData.count || 4,
  order_by: SORT,
  start: getQuery('start') || 0
};

if (document.getElementById('comment-list')) {
  ajax({
    url: REXXAR_API + '/v2/' + TYPE + '/' + SID + '/interests',
    type: 'GET',
    data: data,
    success: function success(ret) {
      ReactDOM.render(React.createElement(CommentList, {
        commentList: ret.interests,
        subject: TalionData.subject,
        hasMore: TalionData.hasMore,
        hasAd: TalionData.hasAd,
        count: TalionData.count }), document.getElementById('comment-list'));
    }
  });
}
;


var cList = document.getElementById('celebrities');
var getClbs = function getClbs() {
  return fetch(REXXAR_API + '/v2/' + TYPE + '/' + SID + '/credits').then(function (r) {
    return r.json();
  });
};

cList && getClbs().then(function (ret) {
  var credits = ret.credits;

  var arr = [].concat(_toConsumableArray(credits));

  function renderClb(clbs, title) {
    return '' + clbs.map(function (c) {
      return '\n         <li class="item item__' + c.type + '">\n          <a href="' + c.url + '">\n            <div class="item-poster" style="background-image: url(' + c.cover_url + ')">\n            </div>\n            <span class="item-title name">' + c.name + '</span>\n            <span class="item-title role">' + title + '</span>\n          </a>\n        </li>';
    }).join('');
  }

  var listTmpl = function listTmpl(arr) {
    return '\n        ' + arr.map(function (credit) {
      return '' + renderClb(credit['celebrities'], credit.title);
    }).join('');
  };

  $(cList).find('ul').append(listTmpl(arr));
}).catch(function (e) {
  return console.log('Err: ' + e.message);
});

var SubjectForumTopics = function (_React$Component5) {
  _inherits(SubjectForumTopics, _React$Component5);

  function SubjectForumTopics() {
    var _ref5;

    var _temp, _this10, _ret;

    _classCallCheck(this, SubjectForumTopics);

    for (var _len2 = arguments.length, args = Array(_len2), _key2 = 0; _key2 < _len2; _key2++) {
      args[_key2] = arguments[_key2];
    }

    return _ret = (_temp = (_this10 = _possibleConstructorReturn(this, (_ref5 = SubjectForumTopics.__proto__ || Object.getPrototypeOf(SubjectForumTopics)).call.apply(_ref5, [this].concat(args))), _this10), _this10.state = {
      start: 0,
      count: 5,
      total: -1,
      forum_topics: [],
      episode: {}
    }, _temp), _possibleConstructorReturn(_this10, _ret);
  }

  _createClass(SubjectForumTopics, [{
    key: 'componentDidMount',
    value: function componentDidMount() {
      this.fetchToForumTopics();
    }
  }, {
    key: 'fetchToForumTopics',
    value: function fetchToForumTopics() {
      var _this11 = this;

      ajax({
        url: REXXAR_API + '/v2/' + this.props.type + '/' + this.props.id + '/forum_topics',
        data: {
          start: this.state.start,
          count: this.state.count
        },
        success: function success(data) {
          _this11.setState(_extends({}, data));
        }
      });
    }
  }, {
    key: 'render',
    value: function render() {
      var pageType = this.props.type === 'tv' ? 'movie' : this.props.type;
      if (this.state.total === -1) {
        return React.createElement('div', { className: 'loading' });
      } else if (this.state.total === 0) {
        return null;
      }
      return React.createElement(
        'div',
        { className: 'subject-forum-topics' },
        React.createElement(
          'h2',
          null,
          '\u8BA8\u8BBA(',
          this.state.total,
          ')'
        ),
        React.createElement(
          'ul',
          { className: 'list' },
          this.state.forum_topics.map(function (topic) {
            return React.createElement(
              'li',
              { key: topic.id },
              React.createElement(
                'a',
                { href: '/' + pageType + '/discussion/' + topic.id },
                React.createElement(
                  'h3',
                  null,
                  topic.title
                ),
                React.createElement(
                  'div',
                  { className: 'info' },
                  topic.comments_count,
                  '\u56DE\u5E94'
                )
              )
            );
          }),
          React.createElement(
            'li',
            { className: 'go-list' },
            React.createElement(
              'a',
              { href: '/' + pageType + '/subject/' + this.props.id + '/discussions' },
              '\u67E5\u770B\u5168\u90E8\u8BA8\u8BBA'
            )
          )
        )
      );
    }
  }]);

  return SubjectForumTopics;
}(React.Component);

;
var $root = document.getElementById('discussions-root');
if ($root) {
  ReactDOM.render(React.createElement(SubjectForumTopics, {
    type: TalionData.subject.type,
    id: TalionData.subject.sid
  }), $root);
}
      })();
   ;
  ;(function() {

    'use strict';

var _extends = Object.assign || function (target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i]; for (var key in source) { if (Object.prototype.hasOwnProperty.call(source, key)) { target[key] = source[key]; } } } return target; };

var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function (obj) { return typeof obj; } : function (obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; };

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

;(function (root, factory) {
  if (typeof define === 'function' && define.amd) {
    define([], factory);
  } else if ((typeof exports === 'undefined' ? 'undefined' : _typeof(exports)) === 'object') {
    module.exports = factory();
  } else {
    if (root.SWCache && root.console && console.warn) {
      console.warn('window.Lazify already exists! Skipped');
    }
    root.SWCache = root.SWCache || factory();
  }
})(window, function () {

  var UA = function () {
    var userAgent = navigator.userAgent || '';

    function detect(pattern) {
      return function () {
        return pattern.test(userAgent);
      };
    }

    return {
      isWeixin: detect(/micromessenger/i),
      isWeibo: detect(/weibo/i),
      isXiaomi: detect(/xiaomi/i),
      isBaidu: detect(/baiduboxapp|baidubrowser/i),
      isIOS: detect(/iphone|ipad|ipod/i),
      isAndroid: detect(/android/i),
      isMobile: detect(/(iphone|ipod|((?:android)?.*?mobile)|blackberry|nokia)/i),
      isFrodo: detect(/com\.douban\.frodo/i)
    };
  }();

  function genHash(str) {
    var hash = 0;
    for (var i in str) {
      hash = (hash << 5) - hash + str.charCodeAt(i);
      hash |= 0;
    }
    return hash;
  }
  ;
  function Debug(prefix, enabled) {
    var debug = void 0;
    if (enabled) {
      Debug.state[prefix] = true;
    }
    var state = prefix in Debug.state ? Debug.state[prefix] : Debug.defaultState;
    if (state === false) {
      debug = Debug.noop;
    } else {
      var color = Debug.selectColor(prefix);
      debug = function debug() {
        var _console;

        for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
          args[_key] = arguments[_key];
        }

        (_console = console).log.apply(_console, ['%c' + prefix + '%c', 'color: ' + color + ';', 'color: inherit;'].concat(args));
      };
      debug.isEnable = true;
    }
    return debug;
  }
  Debug.noop = function () {};
  Debug.noop.isEnable = true;
  Debug.state = {};
  Debug.defaultState = false;

  Debug.enable = function (enable) {
    if ((typeof enable === 'undefined' ? 'undefined' : _typeof(enable)) === 'object') {
      Debug.state = _extends({}, Debug.state, enable);
    } else if (typeof enable === 'string') {
      Debug.state = _extends({}, Debug.state, _defineProperty({}, enable, true));
      return Debug(enable);
    } else {
      Debug.defaultState = enable;
    }
  };

  Debug.selectColor = function (prefix) {
    return Debug.colors[Math.abs(genHash(prefix)) % Debug.colors.length];
  };

  Debug.colors = ['lightseagreen', 'forestgreen', 'goldenrod', 'dodgerblue', 'darkorchid', 'crimson', 'yellow'];

  Debug.logErr = function () {
    if (typeof console !== 'undefined' && console.error) {
      var _console2;

      (_console2 = console).error.apply(_console2, arguments);
    }
  };
  var debug = Debug.noop;
  var defaults = {
    serviceWorkerUrl: '/pwa/cache_worker',
    scope: '/',
    debug: false,
    precache: [],
    currentPath: location.pathname,
    offlineUrl: '/pwa/offline',

    onNetworkChange: null
  };

  var settings = _extends({}, defaults);

  function cloneMsgSettings(settings) {
    return Object.keys(settings).reduce(function (newSettings, key) {
      var type = _typeof(settings[key]);
      if (type !== 'function') {
        newSettings[key] = settings[key];
      }
      return newSettings;
    }, {});
  }

  function start() {
    var options = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};
    var _navigator = navigator,
        serviceWorker = _navigator.serviceWorker;

    if (!serviceWorker) return;
    settings = _extends({}, settings, options);
    if (settings.debug) {
      debug = Debug('pwa:cache', true);
    }

    debug('settings', settings);
    serviceWorker.register(settings.serviceWorkerUrl, { scope: settings.scope }).then(function (registration) {
      debug('register', registration);

      if (UA.isFrodo() || UA.isWeixin() || UA.isWeibo() || UA.isXiaomi() || UA.isBaidu() || typeof DISABLE_SERVICE_WORKER !== 'undefined') {
        return registration.unregister().then(function (ret) {
          return debug('Unregister Service Worker[' + settings.serviceWorkerUrl + ']: ' + ret);
        });
      }

      var messenger = registration.installing || registration.active || serviceWorker.controller;
      messenger.postMessage({ 'action': 'set-settings', 'settings': cloneMsgSettings(settings) });
    }).catch(Debug.logErr);
  }
  return { start: start };
});

    SWCache.start({
      // debug: false,
      currentPath: location.pathname,
      precache: [
        'https://img3.doubanio.com/f/talion/f8b8fa66f110083144daf3f89367db3ef5a0bee1/pics/card/offline/ic_offline.png',
        'https://img3.doubanio.com/f/talion/6cd76c90e48131ae42f06eaa7026efaf8e741758/pics/card/offline/ic_refresh.png',
        'https://img3.doubanio.com/f/talion/41aeed57f92b2d237eb1825b49b728b6c2addd0c/css/card/base.css',
        'https://img3.doubanio.com/f/talion/ee8e0c54293aefb5709ececbdf082f8091ad5e49/js/lib/zepto/zepto.min.js',
        'https://img3.doubanio.com/f/talion/3621d26d02df6545feb8131951a9911066469e23/js/card/main.js',
        'https://img3.doubanio.com/f/talion/edb4a934937733a0163b69e6cc3ad3f689c92d1c/pics/card/defaults/cover.png',
        'https://img3.doubanio.com/f/talion/9c85529e7a0fbe585a2091edd8b9751a1db10ca9/pics/card/defaults/avatar.jpg',
      ],
      offlineUrl: '/pwa/offline',
    })
  }());
  