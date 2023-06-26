export function genUniqueId() : number {

  let max = 100000;

  const randomStr = Math.floor(Math
    .random() * max); // start at index 2 to skip decimal point

  return randomStr;
}

//export default genUniqueId();