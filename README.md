<h1 align="center">
  <br>
  <img src="assets/logo/chickpea-256.png" />
  <br>
  Chickpea
  <br>
</h1>

<h4 align="center">
  Extension of the Java Bean Validation Specification providing useful annotations for common validation formats. 
</h4>



<p align="center">

  <a href="https://codecov.io/gh/jossmoff/chickpea">
    <img src="https://codecov.io/gh/jossmoff/chickpea/branch/main/graph/badge.svg" />
  </a>

  <a href="https://github.com/jossmoff/chickpea/issues">
    <img src="https://img.shields.io/github/issues/jossmoff/chickpea.svg">
  </a>

  <a href="https://github.com/jossmoff/chickpea/pulls">
    <img src="https://img.shields.io/github/issues-pr/jossmoff/chickpea.svg">
  </a>

  <a href="https://github.com/tronprotocol/jossmoff/chickpea/graphs/contributors">
    <img src="https://img.shields.io/github/contributors/jossmoff/chickpea.svg">
  </a>

  <a href="LICENSE">
    <img src="https://img.shields.io/github/license/jossmoff/chickpea.svg">
  </a>
</p>

## 🥳 Creating new `@Annotation`
The best way to getting started with creating a new annotation for chickpea is to use the `create_new_annotation.py` script. This does require python3, but following the prompts when running:
```shell
  python scripts/create_new_annotation.py
```
You can create the base files for a new annotation! Below is an example of the script in action: 

```shell-session
python script/create_new_annotation.py 
🫘 Annotation name: MyNewAnnotation
🫘 Package offset from dev.joss.chickpea.constraints (e.g. str, aws, or something new): new.package
🫘 Author name: Your Name Here
🥳 Successfully Created @MyNewAnnotation
```