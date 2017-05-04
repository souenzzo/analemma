(ns analemma.core
  (:require [reagent.core :as reagent :refer [atom]]
            [clojure.string :as string]))

(defonce state (atom {:text "Hello world!"}))
(def size
  "Tamanho da tela no browser"
  300)
(def view-box
  "Lista com: x-minimo, y-minimo, range do x, range do y"
  [-50 -50 100 100])
(def raio
  "Tamanho padrão da bola"
  5)

(def cor
  "Cor padrão da bola"
  :green)

(defn ponto-simetrico
  [i]
  {:x i :y (- i)})

(defn gerar-estado
  "Função que retorna o estado da aplicação. Aqui que a mágica ocorre
  "
  []
  {:pontos (for [i (range 5)]
             (let [x (* 10 i)]
               (ponto-simetrico x)))})

(defn hello-world
  "Função que renderiza o estado."
  []
  (let [estado @state]
    [:div [:svg {:width    size
                 :view-box (string/join " " view-box)
                 :height   size}
           (for [ponto (get estado :pontos)]
             [:circle {:cx   (get ponto :x)
                       :cy   (get ponto :y)
                       :r    (get ponto :r raio)
                       :fill (get ponto :fill cor)}])]
     [:br]
     [:code (str estado)]
     ]))




(reagent/render-component [hello-world]
                          (do
                            (reset! state (gerar-estado))
                            (. js/document (getElementById "app"))))

(defn on-js-reload []
  (reset! state (gerar-estado))
  )

