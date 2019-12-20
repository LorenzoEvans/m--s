(ns maybe-sheep.pages.posts
    (:require 
     [maybe-sheep.articles.article-content :refer [content-store]]
     [maybe-sheep.routing :refer [path-for]]
     [reagent.core :as reagent]))
  
(def content-list (get-in content-store [:content]))

(def current-post (reagent/atom {:current-post {:post-state nil}}))

(def post-cursor (reagent/cursor current-post [:current-post :post-state]))
;   @current-post
  
(defn posts-page []
    (let [cp post-cursor]
    (fn []
       [:span.w-75.flex.flex-column.justify-evenly.overflow-scroll.content-center.items-center.bw2.b--black
      [:h1.w5.tc.avenir "Maybe Posts"]
      (js/console.log post-cursor)
       [:ul (map (fn [item-id]
                   [:li {:name (str "item-" item-id) :key (str "item-" item-id)}
                    [:a {:href (path-for :item {:item-id item-id})
                         :on-click #(swap! @post-cursor assoc-in [:current-post :post-state] (keyword item-id))} "Item: " item-id]])
                 (keys content-list))]])))

;   (defn posts-page [posts & loading-posts]
;     (fn []
;       [:span.w-75.flex.flex-column.justify-evenly.overflow-scroll.content-center.items-center.bw2.b--black
;        [:h1.w5.tc.avenir "Maybe Posts"]
;        (for [item content-list]
;          (let [kw (first item)
;                {:keys [title url prev]} (second item)]
;            ^{:key kw}
;            [:div.w-100.flex.flex-row.justify-center.ma2
;             [:a.avenir.link.dim.navy.truncate {:href (path-for :post)
;                                                :on-click #(swap! current-post assoc :current-post kw)} title]]))]))