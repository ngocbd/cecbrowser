# Crazy English Community Facebook Browser

This Project is a browserbased crawler to getting Post from member of cec facebook group (https://www.facebook.com/groups/cec.edu.vn/).
Post content will publish at website CEC Website https://cec.net.vn


## Getting Started

THis project build with Java , chromedriver and Selenium

### Prerequisites
-	Download and install Google Chrome .
-	Download chromedriver http://chromedriver.chromium.org/downloads .
-	Create and download service account key https://console.cloud.google.com/iam-admin/serviceaccounts 

### Installing

Rename and edit file SecretTemplate in src/net/cec

```
package net.cec;

public class SecretTemplate {
	  
	static
	{
		System.setProperty("webdriver.chrome.driver",
	            "path/to/chromedriver");
	}
	 public static String email = "yourfacebookemail";
	 public static String password = "yourpassword";
}

```

Config GOOGLE_APPLICATION_CREDENTIALS enviroment variable  


```
export GOOGLE_APPLICATION_CREDENTIALS=PATH/TO/JSON/FILE
```



## Running the tests

Test with gradle test

```
$ gradle clean test
```



## Deployment



## Built With

* [Selenium](https://www.seleniumhq.org/) - The Selenium automates browsers.
* [Gradle](https://gradle.org/) - Gradle build tool.
* [Google cloud](https://cloud.google.com/) - For store data an analytic (datastore,bigquery,pubsub,cloudfunction,appengine ..

## Contributing

Please read [CONTRIBUTING.md] for details on our code of conduct, and the process for submitting pull requests to us.


## Authors

* **Bui Dinh Ngoc** - *Initial work* - [ngocbd](https://github.com/ngocbd)
* **Nguyen Ngoc** - *Initial work* - [nguyenngoc195](https://github.com/nguyenngoc195)

See also the list of [contributors](https://github.com/ngocbd/cecfacebookbrowser/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments


