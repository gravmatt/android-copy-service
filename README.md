# android-copy-service

This service allows you to copy text with emoji to the system clipboard.

Android comes with a clipboard service onboard but it is difficult to use and doesn't work if you want to copy emoji's.

The service is tested on Android 5.1.1 (API Version 22).

## Usage

### Install the app

```
adb -s <emulator-name> install [-r] <app-name>.apk
```

Use `-r` if you want to update the app (reinstall it).

### Copy to clipboard

```
adb -s <emulator-name> push <file-with-content-to-copy> /data/data/com.gravmatt.copyservice/files/copy.txt

adb -s <emulator-name> shell am startservice -n com.gravmatt.copyservice/.CopyService -c copytext
```

## Licence

The MIT License (MIT)

Copyright (c) 2016 René Tanczos

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
