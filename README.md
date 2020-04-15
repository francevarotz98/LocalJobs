# LocalJobs


[![Build Status](https://travis-ci.com/nicomazz/LocalJobs.svg?token=WmaBi7Gj1zqJU5yfifT8&branch=master)](https://travis-ci.com/nicomazz/LocalJobs)
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![License][license-shield]][license-url]



<!-- PROJECT LOGO -->
<br />

<p align="center">
  <a href="https://github.com/nicomazz/LocalJobs">
    <img src="local_jobs.svg" alt="Logo" width="250" height="250">
  </a>
  <h3 align="center">LocalJobs</h3>

  <p align="center">
    Browse jobs and proposals close to you
    <br />
    <a href="https://github.com/nicomazz/LocalJobs/issues">Report Bug</a>
    ·
    <a href="https://github.com/nicomazz/LocalJobs/issues">Request Feature</a>
  </p>

</p>

<!-- TABLE OF CONTENTS -->
## Table of contents

- [LocalJobs](#localjobs)
  - [Table of contents](#table-of-contents)
  - [About The Project](#about-the-project)
  - [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
  - [Usage](#usage)
  - [Roadmap](#roadmap)
  - [Contributing](#contributing)
  - [License](#license)
  - [Contact](#contact)
  - [Acknowledgements](#acknowledgements)


<!-- ABOUT THE PROJECT -->
## About The Project

<!-- Usage gif -->
<img class="shadowed" src="https://i.imgur.com/ixX4wGu.gif"  width="300" style="float: right; margin:20px; box-shadow: 5px 5px 10px grey;" align="right">


LocalJobs is an Android application developed as project for the  *Embedded System* course at the University of Padua during 2019.
It is far from being a final product, but showcase several interesting and recent android aspects, such as:

* [Kotlin](https://kotlinlang.org/)
* [Firebase](https://firebase.google.com/): 
    * [Cloud functions](https://firebase.google.com/docs/functions)
    * [Cloud messaging](https://firebase.google.com/docs/cloud-messaging)
    * [Real-time database](https://firebase.google.com/docs/database)
    * [Authentication](https://firebase.google.com/docs/auth)
* [Jetpack](https://developer.android.com/jetpack)
* CI from [Travis](https://travis-ci.org/)
* [View bindings](https://developer.android.com/docs)
* [Mapbox](https://www.mapbox.com/)

If you are following this couse, and need to create a new project, consider to use the followings:

* [Ktlint](https://github.com/pinterest/ktlint), to automatically format your kotlin code
* [Travis](https://travis-ci.org/) or [CirlceCI](https://circleci.com/) to automatically run tests, and generate apks
* [Robolectric](http://robolectric.org/) to write UI unit tests 

## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites

You should have a working android studio setup.


### Installation

1. Clone the repo
```sh
git clone https://github.com/nicomazz/LocalJobs.git
cd LocalJobs
```
2. Setup Firebase environment. To do that, create a new app in the [Firebase console](https://console.firebase.google.com/) and add the generated `google-services.json` to `/app`

3. Open the project in Android Studio, and build!


<!-- USAGE EXAMPLES -->
## Usage

* Accept permission requests:

<img class="shadowed" src="https://i.imgur.com/gMarS1R.gif"  width="297" >

* Browse jobs and proposals close to you:

<img class="shadowed" src="https://i.imgur.com/E3s9780.jpg"  width="297" > <img class="shadowed" src="https://i.imgur.com/b7B8w85.jpg"  width="297" >

* Send your interest to any job or proposal! If the recipient accepts your request you will be able to contact him:

<img  class="shadowed" src="https://i.imgur.com/HiyyXVI.jpg"  width="297"> <img  class="shadowed" src="https://i.imgur.com/hdXcFsv.jpg"  width="297">

* Filter jobs and proposals according with your criterias:

<div style="text-align:center">
<img class="shadowed" src="https://i.imgur.com/U5OWoy9.jpg"  width="297">
</div>

<!-- ROADMAP -->
## Roadmap

See the [open issues](https://github.com/nicomazz/LocalJobs/issues) for a list of proposed features (and known issues).



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request



<!-- LICENSE -->
## License

Distributed under the GNUv3 License. See `LICENSE` for more information.



<!-- CONTACT -->
## Contact

- Nicolo' Mazzucato - [@nicomazz](https://twitter.com/nicomazz)
- Luca Moroldo - [@lucamoro](https://www.linkedin.com/in/luca-moroldo/)
- Francesco Pham - [@francescopham](https://www.linkedin.com/in/francesco-pham-54128486/)


<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements

* @frankplus
* @lucamoroz
* @nicomazz





[contributors-shield]: https://img.shields.io/github/contributors/nicomazz/LocalJobs.svg?style=flat-square
[contributors-url]: https://github.com/nicomazz/LocalJobs/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/nicomazz/LocalJobs.svg?style=flat-square
[forks-url]: https://github.com/nicomazz/LocalJobs/network/members
[stars-shield]: https://img.shields.io/github/stars/nicomazz/LocalJobs.svg?style=flat-square
[stars-url]: https://github.com/nicomazz/LocalJobs/stargazers
[issues-shield]: https://img.shields.io/github/issues/nicomazz/LocalJobs.svg?style=flat-square
[issues-url]: https://github.com/nicomazz/LocalJobs/issues
[license-shield]: https://img.shields.io/github/license/nicomazz/LocalJobs.svg?style=flat-square
[license-url]: https://github.com/nicomazz/LocalJobs/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/nicomazz
[product-screenshot]: app/src/main/res/drawable/3_screen.jpg
[product-screenshot2]: app/src/main/res/drawable/3_screen_2.jpg

