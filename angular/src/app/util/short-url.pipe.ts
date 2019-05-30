import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'shortUrl'
})
export class ShortUrlPipe implements PipeTransform {

  transform(url: any, args?: any): any {
    if (url && url.length > 3) {
      let result;
      let match;
      if (match = url.match(/^(?:https?:\/\/)?(?:www\.)?([^:\/\n?=]+)/im)) {
        result = match[1];
        if (match = result.match(/^[^.]+\.(.+\..+)$/))
          result = match[1];
      }
      return result;
    }
    return url;
  }

}
