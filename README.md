# OOP.DSAI.20222.17

---

## Table of Contents

- [OOP.DSAI.20222.17](#OOP.DSAI.20222.17)
  - [Table of Contents]()
  - [Background](##Background)
  - [Member]()
  - [Installation]()
  - [Repository Structure]()
- [Contribution](#OOP.DSAI.20222.17)
  - [1. Duong Minh Quan]()
  - [2. Pham Ngoc Quan](##Background)
  - [3. Nguyen Van Quoc]()
  - [4. Bui Minh Quang]()

---

## Background

This is a **sorting visualizer program coded in Java** for these three algorithms: **Bubble Sort**, **Insertion Sort**, and **Quick Sort**, which could allow users to input a list of numbers and select which algorithm they want to use to sort the list.

The program could then display the unsorted list and step through the sorting process one iteration at a time, highlighting the elements being compared and swapped or moved in each step. This would help users understand how each algorithm works and how efficient it is for different types of input data.

With this program, users can visualize the sorting process in action, gaining a deeper understanding of the mechanics of each algorithm. By seeing how the algorithms perform on different types

---

## Member

| Member          | Github                                                   |
| :-------------- | :------------------------------------------------------- | --- |
| Duong Minh Quan | [@2uanDM](https://github.com/2uanDM)                     |
| Pham Ngoc Quan  | [@ngocquanofficial](https://github.com/ngocquanofficial) |
| Nguyen Van Quoc | [@quocnv20214926](https://github.com/quocnv20214926)     |     |
| Bui Minh Quang  | [@bmquang-20214925](https://github.com/bmquang-20214925) |

---

## Repository Structure

<details>
  <summary>Click to show details</summary>

  <div id="markdownContent">

```
│   .gitignore
│   README.md
│
├───.vscode
│       launch.json
│       settings.json
│
├───bin
│   ├───main
│   │   ├───java
│   │   │   │   Main.class
│   │   │   │
│   │   │   ├───controller
│   │   │   │       BubbleSortController.class
│   │   │   │       HomeController.class
│   │   │   │       InsertionSortController.class
│   │   │   │       QuickSortController.class
│   │   │   │       SortController.class
│   │   │   │
│   │   │   └───view
│   │   │           BubbleSortView.fxml
│   │   │           HomeView.fxml
│   │   │           InsertionSortView.fxml
│   │   │           QuickSortView.fxml
│   │   │
│   │   └───resources
│   │       ├───assets
│   │       │   ├───HomeView
│   │       │   │       about_button.png
│   │       │   │       about_hover.png
│   │       │   │       bubbleshort_hover_front.png
│   │       │   │       bubble_sort_bar.png
│   │       │   │       help_button.png
│   │       │   │       help_hover.png
│   │       │   │       insertionsort_hover_front.png
│   │       │   │       insertion_sort_bar.png
│   │       │   │       logo_VIALGO.png
│   │       │   │       quicksort_hover_front.png
│   │       │   │       quick_sort_bar.png
│   │       │   │       test.gif
│   │       │   │
│   │       │   └───SortView
│   │       │           menuActionArrow.png
│   │       │           menuActionColor.png
│   │       │
│   │       ├───sound
│   │       │       chime.wav
│   │       │
│   │       └───style
│   │               home.css
│   │               sort.css
│   │
│   └───test
├───design
│       GeneralClassDiagram.asta
│       GeneralClassDiagram.png
│       UseCaseDiagram.asta
│       UseCaseDiagram.png
│
├───lib
└───sourcecode
    ├───main
    │   ├───java
    │   │   │   Main.java
    │   │   │
    │   │   ├───controller
    │   │   │       BubbleSortController.java
    │   │   │       HomeController.java
    │   │   │       InsertionSortController.java
    │   │   │       QuickSortController.java
    │   │   │       SortController.java
    │   │   │
    │   │   ├───model
    │   │   │   ├───exception
    │   │   │   │       DataTypeException.java
    │   │   │   │       MinMaxValueException.java
    │   │   │   │       NullException.java
    │   │   │   │       NumberOfValueException.java
    │   │   │   │
    │   │   │   ├───object
    │   │   │   │       ColumnBar.java
    │   │   │   │       TextValue.java
    │   │   │   │
    │   │   │   ├───sorting_algo
    │   │   │   │       BubbleSort.java
    │   │   │   │       InsertionSort.java
    │   │   │   │       QuickSort.java
    │   │   │   │       SortingAlgorithm.java
    │   │   │   │
    │   │   │   └───vialgo_utils
    │   │   │           AnimationUtils.java
    │   │   │           ArrayUtils.java
    │   │   │           InputParserUtils.java
    │   │   │           SetVisibleUtils.java
    │   │   │
    │   │   └───view
    │   │           BubbleSortView.fxml
    │   │           HomeView.fxml
    │   │           InsertionSortView.fxml
    │   │           QuickSortView.fxml
    │   │
    │   └───resources
    │       ├───assets
    │       │   ├───HomeView
    │       │   │       about_button.png
    │       │   │       about_hover.png
    │       │   │       bbsort_info.png
    │       │   │       bubbleshort_hover_front.png
    │       │   │       bubble_sort_bar.png
    │       │   │       help_button.png
    │       │   │       help_hover.png
    │       │   │       insertionsort_hover_front.png
    │       │   │       insertion_info.png
    │       │   │       insertion_sort_bar.png
    │       │   │       logo_VIALGO.png
    │       │   │       quicksort_hover_front.png
    │       │   │       quick_sort_bar.png
    │       │   │       quick_sort_info.png
    │       │   │       test.gif
    │       │   │
    │       │   └───SortView
    │       │           aEquals.png
    │       │           backButton.png
    │       │           backButton_hover.png
    │       │           Bubble Sort.png
    │       │           icon.ico
    │       │           icon.png
    │       │           Insertion Sort.png
    │       │           menuActionArrow.png
    │       │           menuActionArrow_left.png
    │       │           menuActionColor.png
    │       │           pseudoCodeColor.png
    │       │           Quick Sort.png
    │       │           restart.png
    │       │           small logo.png
    │       │           sortExplainColor.png
    │       │
    │       ├───sound
    │       │       chime.wav
    │       │
    │       └───style
    │               home.css
    │               sort.css
    │
    └───test
            testing.java
```

  </div>
</details>

---

# Contribution

### 1. Duong Minh Quan

| Work                                                                                                                             | Contribute |
| :------------------------------------------------------------------------------------------------------------------------------- | :--------- |
| [`sourcecode/main/java/controller/HomeController`](./sourcecode/main/java/controller/HomeController.java)                        | `100%`     |
| [`sourcecode/main/java/controller/SortController`](./sourcecode/main/java/controller/SortController.java)                        | `70%`      |
| [`sourcecode/main/java/controller/BubbleSortController`](./sourcecode/main/java/controller/BubbleSortController.java)            | `50%`      |
| [`sourcecode/main/java/controller/InsertionSortController.java`](./sourcecode/main/java/controller/InsertionSortController.java) | `100%`     |
| [`sourcecode/main/java/controller/QuickSortController.java`](./sourcecode/main/java/controller/QuickSortController.java)         | `15%`      |
| [`sourcecode/main/java/view/HomeView.fxml`](./sourcecode/main/java/view/HomeView.fxml)                                           | `100%`     |
| [`sourcecode/main/java/view/BubbleSortView.fxml`](./sourcecode/main/java/view/BubbleSortView.fxml)                               | `70%`      |
| [`sourcecode/main/java/view/InsertionSortView.fxml`](./sourcecode/main/java/view/InsertionSortView.fxml)                         | `70%`      |
| [`sourcecode/main/java/view/QuickSortView.fxml`](./sourcecode/main/java/view/QuickSortView.fxml)                                 | `70%`      |
| [`sourcecode/main/java/Main.java`](./sourcecode/main/java/Main.java)                                                             | `100%`     |
| [`sourcecode/main/java/model/object/ColumnBar.java`](./sourcecode/main/java/model/object/ColumnBar.java)                         | `100%`     |
| [`sourcecode/main/java/model/object/TextValue.java`](./sourcecode/main/java/model/object/TextValue.java)                         | `70%`      |
| [`sourcecode/main/java/model/vialgo_utils/AnimationUtils.java`](./sourcecode/main/java/model/vialgo_utils/AnimationUtils.java)   | `100%`     |
| [`sourcecode/main/java/model/vialgo_utils/SetVisibleUtils.java`](./sourcecode/main/java/model/vialgo_utils/SetVisibleUtils.java) | `100%`     |
| [`sourcecode/main/java/model/sorting_algo/InsertionSort.java`](./sourcecode/main/java/model/sorting_algo/BubbleSort.java)        | `15%`      |
| [`sourcecode/main/resources/`](./sourcecode/main/java/model/sorting_algo/BubbleSort.java)                                        | `100%`     |

### 2. Pham Ngoc Quan

| Work                                                                                                                               | Contribute |
| :--------------------------------------------------------------------------------------------------------------------------------- | :--------- |
| [`sourcecode/main/java/controller/SortController`](./sourcecode/main/java/controller/SortController.java)                          | `30%`      |
| [`sourcecode/main/java/controller/BubbleSortController`](./sourcecode/main/java/controller/BubbleSortController.java)              | `50%`      |
| [`sourcecode/main/java/controller/QuickSortController.java`](./sourcecode/main/java/controller/QuickSortController.java)           | `70%`      |
| [`sourcecode/main/java/model/object/TextValue.java`](./sourcecode/main/java/model/object/TextValue.java)                           | `15%`      |
| [`sourcecode/main/java/model/vialgo_utils/ArrayUtils.java`](./sourcecode/main/java/model/vialgo_utils/ArrayUtils.java)             | `100%`     |
| [`sourcecode/main/java/model/sorting_algo/BubbleSort.java`](./sourcecode/main/java/model/sorting_algo/BubbleSort.java)             | `100%`     |
| [`sourcecode/main/java/model/sorting_algo/SortingAlgorithm.java`](./sourcecode/main/java/model/sorting_algo/SortingAlgorithm.java) | `100%`     |
| [`sourcecode/main/java/model/sorting_algo/InsertionSort.java`](./sourcecode/main/java/model/sorting_algo/InsertionSort.java)       | `85%`      |
| [`sourcecode/main/java/model/vialgo_utils/InputParserUtils.java`](./sourcecode/main/java/model/vialgo_utils/InputParserUtils.java) | `65%`      |
| [`sourcecode/main/java/model/sorting_algo/QuickSort.java`](./sourcecode/main/java/model/sorting_algo/QuickSort.java)               | `25%`      |

### 3. Nguyen Van Quoc

| Work                                                                                                                                     | Contribute |
| :--------------------------------------------------------------------------------------------------------------------------------------- | :--------- |
| [`sourcecode/main/java/controller/QuickSortController.java`](./sourcecode/main/java/controller/QuickSortController.java)                 | `15%`      |
| [`sourcecode/main/java/model/object/TextValue.java`](./sourcecode/main/java/model/object/TextValue.java)                                 | `15%`      |
| [`sourcecode/main/java/model/exception/DataTypeException.java`](./sourcecode/main/java/model/exception/DataTypeException.java)           | `100%`     |
| [`sourcecode/main/java/model/exception/MinMaxValueException.java`](./sourcecode/main/java/model/exception/MinMaxValueException.java)     | `100%`     |
| [`sourcecode/main/java/model/exception/NullException.java`](./sourcecode/main/java/model/exception/NullException.java)                   | `100%`     |
| [`sourcecode/main/java/model/exception/NumberOfValueException.java`](./sourcecode/main/java/model/exception/NumberOfValueException.java) | `100%`     |
| [`sourcecode/main/java/model/vialgo_utils/InputParserUtils.java`](./sourcecode/main/java/model/vialgo_utils/InputParserUtils.java)       | `35%`      |
| [`sourcecode/main/java/model/sorting_algo/QuickSort.java`](./sourcecode/main/java/model/sorting_algo/QuickSort.java)                     | `25%`      |

### 4. Bui Minh Quang

| Work                                                                                                                 | Contribute |
| :------------------------------------------------------------------------------------------------------------------- | :--------- |
| [`sourcecode/main/java/view/BubbleSortView.fxml`](./sourcecode/main/java/view/BubbleSortView.fxml)                   | `30%`      |
| [`sourcecode/main/java/view/InsertionSortView.fxml`](./sourcecode/main/java/view/InsertionSortView.fxml)             | `30%`      |
| [`sourcecode/main/java/view/QuickSortView.fxml`](./sourcecode/main/java/view/QuickSortView.fxml)                     | `30%`      |
| [`sourcecode/main/java/model/sorting_algo/QuickSort.java`](./sourcecode/main/java/model/sorting_algo/QuickSort.java) | `50%`      |
| [`design/GeneralClassDiagram.png`](./sourcecode/main/java/model/sorting_algo/QuickSort.java)                         | `100%`     |
