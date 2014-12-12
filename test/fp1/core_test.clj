(ns fp1.core-test
  (:require [clojure.test :refer :all]
            [fp1.core :refer :all]))

(deftest extract_points-test
  (testing "extract_points should return points from file."
    (is
      (= (extract_points "resources/бабочка.txt")
        [{:coordinates [0 3], :potential nil} {:coordinates [1 5], :potential nil} {:coordinates [2 4], :potential nil} {:coordinates [3 3], :potential nil} {:coordinates [2 2], :potential nil} {:coordinates [2 1], :potential nil} {:coordinates [1 0], :potential nil} {:coordinates [5 5], :potential nil} {:coordinates [6 5], :potential nil} {:coordinates [7 6], :potential nil} {:coordinates [5 3], :potential nil} {:coordinates [7 3], :potential nil} {:coordinates [6 2], :potential nil} {:coordinates [6 1], :potential nil} {:coordinates [8 1], :potential nil}]))))

(deftest distance-test
  (testing "distance should return square of distance between points."
    (is
      (=
        (distance
          {:coordinates [0 0]}
          {:coordinates [3 4]}
          0)
        25.0))))

(deftest potential-relatively-to-others-test
  (testing "potential-relatively-to-others should calculate point potential relatively to multiple points."
    (is
      (=
        (potential-relatively-to-others
          {:coordinates [0 0], :potential 1}
          [{:coordinates [1 2], :potential 1}, {:coordinates [3 4], :potential 3}, {:coordinates [1 1], :potential 2}])
        2.0))))

(deftest split_by_max_potential-test
  (testing "split_by_max_potential should split list of points like ((max_potential_point)(rest_points))"
    (def points [{:coordinates [0 0], :potential 1}, {:coordinates [0 0], :potential 3}, {:coordinates [0 0], :potential 2}])
    (def points_result [[{:coordinates [0 0], :potential 3}][{:coordinates [0 0], :potential 1} {:coordinates [0 0], :potential 2}]])
    (is
      (=
        (split_by_max_potential points)
        points_result))))

(deftest potentialized_points-test
  (testing "potentialized_points should add potentials to points"
    (def points [{:coordinates [1 2], :potential 1}, {:coordinates [3 4], :potential 3}, {:coordinates [1 1], :potential 2}])
    (def points_result [{:coordinates [1 2], :potential 1.669745889214505} {:coordinates [3 4], :potential 1.0316610876369157} {:coordinates [1 1], :potential 1.6442759752823197}])
    (is
      (=
        (potentialized_points points)
        points_result))))
