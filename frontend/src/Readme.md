# Overview

This application was generated with a custom React build.
It still works similar to Create-React-App, but with some added functionality.

I have place markdown files throughout the code base to elaborate on what each area does. This is the overview area of the project. Here is where I will list all relevant information to the structure such as frameworks, technologies, packages, an etc that are used within.

### Sectional Overlay
I've split the file structure up to isolate things a bit. The **assets** folder is where all of the images and additional pieces of the project will go. **Styles** will handle any custom styling that may be done to the project as need be. **Pages**, this one will see changes as React Router gets implemented. Essentially, this folder wiil contain the main components that will serve as the various "pages" in our application. **Components** is made up of different folders that will hold the different components that will make up the pages. Conceptually, some of this may overlap as some components can be reused.

### Styling
You may notice that there are no css files. Instead, there are SASS files. [SASS](https://sass-lang.com/) is an extension of CSS that adds extra features to the code. I will leave comments in the files if anything is unclear.

Aside from that, [React Shards](https://designrevision.com/docs/shards-react/component/alert) is the UI framework I chose to use. There were many options available, including Bootstrap, but I went with Shards due to it's lightweight nature, ease of use, and customization options. 

### JavaScript
