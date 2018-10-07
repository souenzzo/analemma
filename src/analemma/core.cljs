(ns analemma.core
  (:require [reagent.core :as r]
            [clojure.string :as string]))

(def size
  "Tamanho da tela no browser"
  300)

(def view-box
  "Lista com: x-minimo, y-minimo, range do x, range do y"
  [-10 -490 490 490])

(def raio
  "Tamanho padrão da bola"
  2)

(def cor
  "Cor padrão da bola"
  :green)

(defn ponto-simetrico
  [i]
  {:x i :y (- i)})

(defn gerar-estado
  "Função que retorna o estado da aplicação. Aqui que a mágica ocorre"
  []
  {:pontos (for [i (range 50)]
             (let [x (* 10 i)]
               (ponto-simetrico x)))})

(defonce state (r/atom (gerar-estado)))

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
     #_[:code (str estado)]]))

(r/render-component [hello-world] (.getElementById js/document "app"))

(defn on-jsload
  []
  (reset! state (gerar-estado)))
