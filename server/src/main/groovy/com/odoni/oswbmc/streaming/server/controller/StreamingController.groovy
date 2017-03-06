package com.odoni.oswbmc.streaming.server.controller

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody

@RestController
@RequestMapping(value = "/streaming")
class StreamingController {

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET)
    StreamingResponseBody streamMovie(@PathVariable(value="id") String id) {

        //TODO - Retrieve file properly
        InputStream videoFile = new FileInputStream("/file.avi")

        new StreamingResponseBody() {
            @Override
            void writeTo(OutputStream outputStream) throws IOException {
                readAndWrite(videoFile, outputStream)
            }
        }

    }

    private void readAndWrite(final InputStream is, OutputStream os)
            throws IOException {
        byte[] data = new byte[2048]
        int read = 0
        while ((read = is.read(data)) > 0) {
            os.write(data, 0, read)
        }
        os.flush()
    }




}
