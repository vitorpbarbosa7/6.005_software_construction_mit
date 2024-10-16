/*
 * 6.005 edX Script
 */

(function() {
  
  // Avoid running this script twice.
  if (window.six005edx) { return; }
  window.six005edx = true;


  // Convert blocks of the form:
  //    ||| 
  //    ... Java code here ... 
  //    |||
  // into syntax-highlighted <pre><code> elements.
  //
  // Attaches a MutationObserver so that when new elements appear (like Explanations) they
  // also get the conversion.
  (function() {
    var content = document.getElementById('course-content');
    
    // either we're in the studio, or we're hosed
    if ( ! content) { return; }
    
    // re-render problems/solutions as they come and go
    new MutationObserver(rerender).observe(content, { childList: true, subtree: true });
    
    function rerender() {
      // XXX TODO don't just transform .problem blocks
      // handle code blocks
      var fences = $('.problem p', content).filter(function() {
        return this.textContent.indexOf('|||') === 0;
      });
      for (var ii = 0; ii < fences.length; ii += 2) {
        var attrs = fences[ii].textContent.match(/\|\|\|(\w*)\s*(.*)/);
        var lang = attrs[1] || 'java';
        var style = attrs[2];
        
        var block = $(fences[ii]).nextUntil(fences[ii+1]);
        var text = block.map(function() { return this.textContent; }).toArray().join("\n");
        
        block.addBack().remove();
        var code = $('<code>').addClass('language-'+lang).text(text);
        var pre = $('<pre>').addClass(style).append(code);
        $(fences[ii+1]).replaceWith(pre);
        try {
          Prism.highlightElement(code[0]);
        } catch (e) {
          // sometimes races with loading prism.js, so display these errors but then move on,
          // to make sure we convert all the code
          console.log(e);
        }
      }
      
      // if answer labels change, they are never correct
      // so this doesn't work:  $('.problem label:contains("|")').each(render_spans);
      // handle all-code labels as a compromise
      var tags = $('.problem p', content).filter(function() { return this.textContent == '|codelabels|'; });
      tags.next().find('form').addClass('code-answers');
      tags.remove();
      
      // and handle code spans in problem & solution text
      // TODO if we fail to replace any pipes, we're going to get called again, and infinite loop
      // TODO update to test for regex match instead of mere pipes
      // TODO and update to allow for code containing the || operator
      $('.problem p:contains("|")', content).each(render_spans);
      
      function render_spans() {
        this.innerHTML = this.innerHTML.replace(/(^|\s)\|([^|]+)\|/gm, '$1<code>$2</code>');
      }
    }
    
    rerender();
  }) ();


  // Whenever the learner clicks Check on a problem and gets everything right,
  // this code automatically pushes Show Answer for them as well, so that they
  // see the Explanation.
  //
  // Set the click handler on the whole course-content page because Check
  // buttons and Show Answer buttons are being constantly created and destroyed.
  $(".course-content")[0].addEventListener("click", function(event) {
      //console.log(event.target);
      if ($(event.target).closest("button.check").length > 0) {
          //console.log("clicked Check");

          var problem = $(event.target).closest(".problems-wrapper");
          //console.log(problem);
          // This <div class=problems-wrapper> is the only div that we can reliably hold onto while
          // we're waiting for the Check operation to complete.
          // Everything inside it gets deleted and recreated by edX when the user pushes Check.
          // Including the Check button itself!
          // So whenever we need to find the Check button, or answers, or the Show Answer button,
          // we always have to start with problem.find(). 

          repeatUntilTrue(function() {
              return forceShowAnswerIfAllAnswersCorrect(problem);
          }, 200, 25);
      }

      function forceShowAnswerIfAllAnswersCorrect(problem) {
          var checkButton = problem.find("button.check");
          if (checkButton.hasClass("is-disabled")) { 
            //console.log("Check button still disabled");
            return false;
          }
          if (!allAnswersCorrect(problem)) { 
            //console.log("not yet all answers correct");
            return false;
          }
          //console.log("all answers right!");

          // try to scroll the student's answer back into view,
          // because edX sometimes jumps to the bottom of the page
          try {
            problem.find("form").get(0).scrollIntoView();
          } catch (e) {}

          // click the Show Answer button after a bit of a delay
          setTimeout(function() {
              problem.find("button.show:contains(Show Answer)").click();
          }, 500);

          return true;
      }

      function allAnswersCorrect(problem) {
          statuses = problem.find("span.status").text();
          //console.log(statuses);
          return statuses.indexOf("correct") != -1
              && statuses.indexOf("incorrect") == -1
              && statuses.indexOf("unanswered") == -1;
      }

      function repeatUntilTrue(f, intervalMilliseconds, maxRepeats) {
          if (maxRepeats == 0) {
              //console.log("giving up");
          } else {
              setTimeout(function() {
                  if (!f()) {
                      repeatUntilTrue(f, intervalMilliseconds, maxRepeats-1);
                  }
              }, intervalMilliseconds);
          }
      }
  }, true);

})();
