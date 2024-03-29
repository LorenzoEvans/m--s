(ns maybe-sheep.pages.post
    (:require [maybe-sheep.articles.article-content :refer [content-store]]
              [maybe-sheep.routing :refer [path-for]]
              [maybe-sheep.pages.posts :refer [current-post]]))
  
  
  ; (def current-post (atom {:current-post nil}))
(def content-list (get-in content-store [:content]))
(def key-list (keys content-list))
  ; @current-post
  ; so if we pull all of the keys out, we have them in the post but how to connect them?

  (defn post-page []
    (fn [& e]
      [:div.w-75
       (let [cp @current-post
             title (get-in content-list [(:current-post cp) :title])
             prev (get-in content-list [(:current-post cp) :prev])
             full-article (get-in content-list [(:current-post cp) :full-article])]
         [:span.flex.flex-column.justify-between.content-center.items-center.h-100.overflow-scroll
          [:h1.avenir title]
        (js/console.log key-list @current-post)
          [:div.avenir.near-black prev]
          [:div.flex.justify-center.article-height full-article]
          [:p [:a.avenir.link.dim.navy {:href (path-for :posts)} "Back to the list of items"]]])]))
  
  