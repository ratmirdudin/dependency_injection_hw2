## Mail Ru Games courses
## Homework #2: Dependencies injecting by Guice
### Launch
```shell
./gradlew task runMainClass
```
`Or start program using task panel of Gradle.`

To change the input parameter, open the settings file with the task runMainClass: `build.gradle` .

3 mods here:
+ console(args 'console')
+ file(args 'file *tag*')
+ composite(args 'composite *tag*')

Where *tag* is tag for log's file.

With file and composite mods log file is saving in `C:\Users\<Username>\java_dependency_injection`